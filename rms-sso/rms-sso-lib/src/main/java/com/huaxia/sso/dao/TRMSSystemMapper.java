package com.huaxia.sso.dao;

import com.huaxia.sso.model.TRMSSystem;

public interface TRMSSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRMSSystem record);

    int insertSelective(TRMSSystem record);

    TRMSSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRMSSystem record);

    int updateByPrimaryKey(TRMSSystem record);
}