package com.huaxia.sso.dao;

import com.huaxia.sso.model.TRMSUserDetail;

public interface TRMSUserDetailMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(TRMSUserDetail record);

    int insertSelective(TRMSUserDetail record);

    TRMSUserDetail selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TRMSUserDetail record);

    int updateByPrimaryKey(TRMSUserDetail record);
}