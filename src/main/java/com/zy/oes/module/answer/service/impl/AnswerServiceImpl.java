package com.zy.oes.module.answer.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.ApiUtil;
import com.zy.oes.common.util.BeanExpandUtil;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.answer.entity.Answer;
import com.zy.oes.module.answer.entity.SingleAnswer;
import com.zy.oes.module.answer.entity.dto.AnswerDTO;
import com.zy.oes.module.answer.entity.dto.GetAnswerDetailDTO;
import com.zy.oes.module.answer.entity.dto.GetAnswerPageDTO;
import com.zy.oes.module.answer.entity.dto.ModifyAnswerDTO;
import com.zy.oes.module.answer.entity.vo.AnswerDetailVO;
import com.zy.oes.module.answer.entity.vo.AnswerVO;
import com.zy.oes.module.answer.mapper.AnswerMapper;
import com.zy.oes.module.answer.service.IAnswerService;
import com.zy.oes.module.exam.entity.Exam;
import com.zy.oes.module.exam.service.IExamService;
import com.zy.oes.module.paper.entity.vo.PaperDetailVO;
import com.zy.oes.module.paper.service.IPaperService;
import com.zy.oes.module.question.entity.vo.*;
import com.zy.oes.module.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class AnswerServiceImpl extends BaseServiceImpl<AnswerMapper, Answer> implements IAnswerService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IExamService examService;

    @Autowired
    private IPaperService paperService;

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${oes.path}")
    private String baseFilePath;

    @Override
    public PageInfo<AnswerVO> getAnswerPage(GetAnswerPageDTO dto) {
        if (dto.getPageNum() < 0 || dto.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        if (examService.getById(dto.getExamId()) == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "考试信息不存在");
        }
        // 分页查询
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Answer> list = this.baseMapper.selectList(new QueryWrapper<Answer>().eq("examinee_id", dto.getUserId()));
        if (list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        // 实体对象列表转换VO列表
        return new PageInfo<>(BeanExpandUtil.toVOList(list, AnswerVO.class));
    }

    @Override
    public AnswerDetailVO getAnswerDetail(GetAnswerDetailDTO dto) {
        if (this.examService.getById(dto.getExamId()) == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "考试信息不存在");
        }
        if (this.userService.getById(dto.getUserId()) == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "考生信息不存在");
        }
        // 查询试卷基本信息
        Answer answer = this.baseMapper.selectOne(new QueryWrapper<Answer>()
                .eq("exam_id", dto.getExamId())
                .eq("examinee_id", dto.getUserId()));
        AnswerDetailVO vo = new AnswerDetailVO();
        BeanUtils.copyProperties(answer, vo);
        // 获取Excel文件中的答卷内容
        vo.setAnswers(readAnswer(answer.getExamId(), answer.getContentFile()));
        return vo;
    }

    @Override
    public ApiResult<?> addAnswer(AnswerDTO dto) {
        // 验证考试信息
        Exam exam = examService.getById(dto.getExamId());
        if (exam == null) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        // 验证考试是否过期
//        if (exam.getEndTime().before(new Date())) {
//            throw new ApiException(ResultCode.ADD_FAIL, "考试已结束");
//        }
        // 用户id
        Long userId = tokenUtil.getCurrentUser().getId();
        // 文件名
        String fileName = userId + ".xlsx";
        // 数据库存储
        Answer answer = new Answer();
        answer.setExamId(dto.getExamId());
        answer.setExamineeId(userId);
        answer.setContentFile(fileName);
        // 计算答题分数和统计总分
        answer.setTotalScore(calculateTotalScore(exam.getPaperId(), dto.getAnswers()));
        if (this.add(answer) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        // 答卷内容转存Excel文件
        storeAnswer(dto.getAnswers(), fileName, dto.getExamId());
        return ApiUtil.success(ResultCode.SUCCESS, "存储成功");
    }

    @Override
    public ApiResult<?> modifyAnswer(ModifyAnswerDTO dto) {
        Answer answer = this.baseMapper.selectById(dto.getId());
        if (answer == null) {
            throw new ApiException(ResultCode.MODIFY_FAIL, "答卷不存在");
        }
        // 修改的序号列表
        List<Integer> nos = dto.getQNos();
        // 修改的分数列表
        List<Double> scores = dto.getScores();
        if (nos.size() != scores.size()) {
            throw new ApiException(ResultCode.MODIFY_FAIL, "修改信息有误");
        }
        // 读取Excel文件中的答卷内容
        // 答卷内容
        List<SingleAnswer> answers = readAnswer(answer.getExamId(), answer.getContentFile());
        // 转换为map，以试题序号为键，方便修改分数
        Map<Integer, SingleAnswer> answerMap = answers.stream().collect(Collectors.toMap(SingleAnswer::getNo, que -> que));
        // 原总分数
        double totalScore = Double.parseDouble(answer.getTotalScore().toString());
        for (int i = 0; i < nos.size(); i++) {
            SingleAnswer singleAnswer = answerMap.get(nos.get(i));
            if (singleAnswer != null) {
                // 修改总分
                totalScore += singleAnswer.getScore() - scores.get(i);
                // 修改单题分数
                singleAnswer.setScore(scores.get(i));
            }
        }

        // 存储到Excel中
        storeAnswer(answers, answer.getContentFile(), answer.getExamId());

        // 修改数据库中总分数
        Answer newAnswer = new Answer();
        newAnswer.setId(answer.getId());
        newAnswer.setTotalScore(new BigDecimal(totalScore));
        if (this.baseMapper.updateById(newAnswer) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        return ApiUtil.success("修改成功");
    }

    /**
     * @title calculateTotalScore
     * @description <p> 计算答卷分数 </p>
     * @date 2023/3/23 18:09
     * @author MoZhu
     * @param paperId 试卷id
     * @param list 答题列表
     * @return {@link BigDecimal}
     */
    private BigDecimal calculateTotalScore(Long paperId, List<SingleAnswer> list) {
        Double totalScore = 0.0;
        PaperDetailVO paperDetail = this.paperService.getPaperDetail(paperId);
        // 将试卷所有试题转换为map(试题id， 试题);
        Map<Long, QueVO> queMap = new HashMap<>();
        queMap.putAll(paperDetail.getChoiceQues().stream().collect(Collectors.toMap(ChoiceQueVO::getId, que -> que)));
        queMap.putAll(paperDetail.getBlankQues().stream().collect(Collectors.toMap(BlankQueVO::getId, que -> que)));
        queMap.putAll(paperDetail.getJudgeQues().stream().collect(Collectors.toMap(JudgeQueVO::getId, que -> que)));
        queMap.putAll(paperDetail.getSubQues().stream().collect(Collectors.toMap(SubQueVO::getId, que -> que)));

        // 判断正误 + 计算总分
        for (SingleAnswer answer : list) {
            QueVO que = queMap.get(answer.getQId());
            if (que.getStdAns().equals(answer.getContent())) {
                answer.setScore(que.getScore());
                totalScore += que.getScore();
            }
        }
        return new BigDecimal(totalScore);
    }

    /**
     * @title storeAnswer
     * @description <p> 答卷转存excel文件 </p>
     * @date 2023/3/21 18:33
     * @author MoZhu
     * @param list 答题列表
     * @param fileName 文件名
     * @param examId 考试id
     */
    private void storeAnswer(List<SingleAnswer> list, String fileName, Long examId) {
        StringBuilder builder = new StringBuilder();
        // 组成excel文件存储路径
        builder.append(baseFilePath).append(examId);
        builder.append(File.separator).append(fileName);

        // 存储文件
        File xlsx;
        try {
            // 创建文件
            xlsx = FileUtil.touch(builder.toString());
            // 获取excel文件输出流
            ExcelWriter writer = ExcelUtil.getWriter(xlsx);
            // 设置列别名
            writer.addHeaderAlias("no", "答题序号");
            writer.addHeaderAlias("qId", "试题id");
            writer.addHeaderAlias("content", "答题内容");
            writer.addHeaderAlias("score", "答题得分");
            // 写入数据
            writer.write(list,true);
        } catch (Exception e) {
            throw new ApiException(ResultCode.FILE_STORE_FAIL);
        }
    }

    /**
     * @title readAnswer
     * @description <p> 读取答题内容 </p>
     * @date 2023/3/21 18:57
     * @author MoZhu
     * @param examId 考试id
     * @param fileName 文件名
     * @return {@link List<Answer>}
     */
    private List<SingleAnswer> readAnswer(Long examId, String fileName) {
        // 组成文件路径
        File file = new File(baseFilePath + examId + File.separator + fileName);
        if (!FileUtil.exist(file)) {
            throw new ApiException(ResultCode.FILE_READ_FAIL, "文件不存在");
        }
        ExcelReader reader = ExcelUtil.getReader(file);
        reader.addHeaderAlias("答题序号", "no");
        reader.addHeaderAlias("试题id", "qId");
        reader.addHeaderAlias("答题内容", "content");
        reader.addHeaderAlias("答题得分", "score");
        return reader.readAll(SingleAnswer.class);
    }
}
