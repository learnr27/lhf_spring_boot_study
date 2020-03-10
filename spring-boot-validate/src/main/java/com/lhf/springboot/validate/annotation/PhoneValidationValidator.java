package com.lhf.springboot.validate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: PhoneValidationValidator
 * @Author: liuhefei
 * @Description: 自定义需要实现ConstraintValidator
 * @Date: 2019/7/24 11:56
 */
public class PhoneValidationValidator implements ConstraintValidator<PhoneValidation, String> {
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$"
    );

    @Override
    public void initialize(PhoneValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.length() == 0){
            return true;
        }
        Matcher m = PHONE_PATTERN.matcher(value);
        return m.matches();
    }
}
