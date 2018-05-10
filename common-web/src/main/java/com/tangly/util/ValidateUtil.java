package com.tangly.util;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * 字段验证参数工具
 * @author tangly
 */
public class ValidateUtil {
    
    public static <T> void validate(T obj) {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .buildValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        //抛出检验异常
        Iterator<ConstraintViolation<T>> iter = constraintViolations.iterator();
        while (iter.hasNext()) {
            ConstraintViolation<T> error = iter.next();
            StringBuffer buffer = new StringBuffer()
                    .append(error.getMessage());
            throw new IllegalArgumentException(buffer.toString());
        }

    }

}