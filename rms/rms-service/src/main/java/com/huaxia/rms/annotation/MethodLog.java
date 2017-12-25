package com.huaxia.rms.annotation;

import java.lang.annotation.*;

/**
 * 
 * @author yaoy
 * 
 */
@Target({ ElementType.METHOD, ElementType.TYPE })//目标是方法
@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented    //文档生成时，该注解将被包含在javadoc中，可去掉
public @interface MethodLog {
	String remark() default "";
	String operType() default "0";
	String modelName() default "";
	String modelCode() default "";
}
