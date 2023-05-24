package com.yuanlrc.campus_market.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体检验自定义注解类，根据我们自定义的注解去检查实体各个字段是否在规定的值内
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateEntity {
	
	public boolean required() default false;//是否检验null
	
	public boolean requiredLeng() default false;//是否检验检验长度
	
	public boolean requiredMaxValue() default false;//是否检验最大值
	
	public boolean requiredMinValue() default false;//是否检验最小值
	
	public int maxLength() default -1;//最大长度
	
	public int minLength() default -1;//最小长度
	
	public long maxValue() default -1;//大值
	
	public long minValue() default -1;//最小值
	
	public String errorRequiredMsg() default "";//值为null时的错误提示信息
	
	public String errorMinLengthMsg() default "";//最小长度不满足时的提示信息
	
	public String errorMaxLengthMsg() default "";//最大长度不满足时的提示信息
	
	public String errorMinValueMsg() default "";//最小值不满足时的提示信息
	
	public String errorMaxValueMsg() default "";//最大值不满足时的提示信息
}
