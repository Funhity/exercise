package com.huaxia.sso.dao;

import com.huaxia.sso.model.TRMSUserLoginLog;

public interface TRMSUserLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TRMSUserLoginLog record);

    int insertSelective(TRMSUserLoginLog record);

    TRMSUserLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TRMSUserLoginLog record);

    int updateByPrimaryKey(TRMSUserLoginLog record);
}