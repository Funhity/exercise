package com.huaxia.rms.constant;


import com.huaxia.rms.pojo.ResultCode;

public class CodeConstant {

    public final static ResultCode CSRF_ERROR = new ResultCode("101", "CSRF ERROR:无效的token，或者token过期");
}
