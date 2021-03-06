package finki.ukim.mk.soatickets.core.helpers;

import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DarkoM on 22.10.2017.
 */
public class ErrorMessageHandler {
    public static List<String> ParseErrors(List<FieldError> errors){
        List<String> errorMessages = errors.stream().map(error -> error.getField() + " - " + error.getDefaultMessage()).collect(Collectors.toList());

        return errorMessages;
    }
}