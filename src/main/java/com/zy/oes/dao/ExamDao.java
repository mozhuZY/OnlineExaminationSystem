package com.zy.oes.dao;

import com.zy.oes.entity.Exam;

public interface ExamDao {
    /**
     * 通过id删除考试
     * @param examId 考试id
     * @return sql执行结果
     */
    int deleteByPrimaryKey(Integer examId);

    /**
     * 添加考试
     * @param record 考试
     * @return sql执行结果
     */
    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer examId);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
}