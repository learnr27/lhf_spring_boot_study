package com.lhf.springboot.validate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: CheckCaseValidator
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 10:08
 */
public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseMode caseMode;


    @Override
    public void initialize(CheckCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || "".equals(value.trim())){
            return false;
        }

        switch (this.caseMode){
            case LOWER:
                return value.equals(value.toLowerCase());
            case UPPER:
                return value.equals(value.toUpperCase());
                default:
                    return false;
        }
    }
}
