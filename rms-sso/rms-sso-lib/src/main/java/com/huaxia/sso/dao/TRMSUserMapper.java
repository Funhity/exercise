package com.huaxia.sso.dao;

import com.huaxia.sso.model.TRMSUser;

public interface TRMSUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRMSUser record);

    int insertSelective(TRMSUser record);

    TRMSUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRMSUser record);

    int updateByPrimaryKey(TRMSUser record);

    TRMSUser selectByUserName(String username);

    TRMSUser selectByMobile(String mobile);
}