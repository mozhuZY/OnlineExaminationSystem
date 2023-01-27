package com.zy.oes.dao;

import com.zy.oes.entity.Answer;

public interface AnswerDao {
    /**
     * 通过id删除答卷
     * @param ans_id 答卷id
     * @return sql执行结果
     */
    int deleteByPrimaryKey(Integer ans_id);

    /**
     * 添加新答卷
     * @param record 答卷
     * @return sql执行结果
     */
    int insert(Answer record);

    /**
     * 添加新答卷（可选填字段）
     * @param record 答卷
     * @return sql执行结果
     */
    int insertSelective(Answer record);

    /**
     * 通过id查询答卷
     * @param ans_id 答卷id
     * @return 所查询的答卷
     */
    Answer selectByPrimaryKey(Integer ans_id);

    /**
     * 通过id更新答卷（可选字段）
     * @param record 答卷
     * @return sql执行结果
     */
    int updateByPrimaryKeySelective(Answer record);

    /**
     * 通过id更新答卷
     * @param record 答卷
     * @return sql执行结果
     */
    int updateByPrimaryKey(Answer record);
}