package com.lhf.springboot.validate.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @ClassName: PhoneValidation
 * @Author: liuhefei
 * @Description: 自定义校验手机号码的注解
 * @Date: 2019/7/24 11:52
 */
@Documented
//指定真正实现校验规则的类
@Constraint(validatedBy = PhoneValidationValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface PhoneValidation {

    String message() default "手机号码有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PhoneValidation[] value();
    }
}
