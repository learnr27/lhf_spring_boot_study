package com.lhf.springboot.validate.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ClassName: CheckCase
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 10:01
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//指定真正实现校验规则的类
@Constraint(validatedBy = CheckCaseValidator.class)
@Documented
public @interface CheckCase {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CaseMode value() default CaseMode.LOWER;

}
