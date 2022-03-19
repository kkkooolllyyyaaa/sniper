package com.tsypk.sniper.network;

import com.tsypk.sniper.exception.BadRequestException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

/**
 * @author tsypk on 25.02.2022 14:56
 * @project sniper
 */
@Component
public class UserRequestValidator {

    public void validate(UserDTO user) throws BadRequestException {
        Set<ConstraintViolation<UserDTO>> violations =
                Validation.buildDefaultValidatorFactory().getValidator().validate(user);

        if (violations.size() == 0)
            return;

        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<UserDTO> violation : violations) {
            message.append(violation.getMessage());
        }
        throw new BadRequestException(message.toString());
    }
}
