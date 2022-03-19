package com.tsypk.sniper.network;

import com.tsypk.sniper.exception.BadRequestException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

/**
 * @author tsypk on 25.02.2022 15:01
 * @project sniper
 */
@Component
public class PointRequestValidator {
    public void validate(PointDTO point) throws BadRequestException {
        Set<ConstraintViolation<PointDTO>> violations =
                Validation.buildDefaultValidatorFactory().getValidator().validate(point);

        if (violations.size() == 0)
            return;

        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<PointDTO> violation : violations) {
            message.append(violation.getMessage());
        }
        throw new BadRequestException(message.toString());
    }
}
