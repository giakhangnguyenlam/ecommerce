package com.khangnlg.validator;

import com.khangnlg.exception.ObjectNotValidException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T> {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void valid(T model) throws ObjectNotValidException {
        Set<ConstraintViolation<T>> validations = validator.validate(model);
        if(!validations.isEmpty()){
            List<String> errors = validations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            throw new ObjectNotValidException(StringUtils.joinWith(" | ", errors));
        }
    }

}
