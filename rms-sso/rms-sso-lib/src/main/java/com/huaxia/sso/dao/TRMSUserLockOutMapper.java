package com.huaxia.sso.dao;

import com.huaxia.sso.model.TRMSUserLockOut;

public interface TRMSUserLockOutMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TRMSUserLockOut record);

    int insertSelective(TRMSUserLockOut record);

    TRMSUserLockOut selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TRMSUserLockOut record);

    int updateByPrimaryKey(TRMSUserLockOut record);

    TRMSUserLockOut selectByToday(int id);
}