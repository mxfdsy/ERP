package com.lmfun.common.validate;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class EntityValidator {
    public static <T> ValidateResult validateEntity(T domain) {
        return validate(domain);
    }

    public static <T> ValidateResult validate(T domain, Class<?>... groups) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return convert(validator.validate(domain, groups), new ValidateResult());
    }

    public static <T> ValidateResult validate(T domain, String property, Class<?>... groups) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return convert(validator.validateProperty(domain, property, groups), new ValidateResult());
    }

    private static <T> ValidateResult convert(Set<ConstraintViolation<Object>> vr, ValidateResult r) {
        vr.forEach((cv) -> r.addErrorMessage(cv.getMessage()));
        return r;
    }

}
