package com.zy.oes.dao;

import com.zy.oes.entity.Examinee;

public interface ExamineeDao {
    int deleteByPrimaryKey(Integer examineeId);

    int insert(Examinee record);

    int insertSelective(Examinee record);

    Examinee selectByPrimaryKey(Integer examineeId);

    int updateByPrimaryKeySelective(Examinee record);

    int updateByPrimaryKey(Examinee record);
}