package lv.javaguru.java2.validators;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;


/**
 * Created by Pavel on 12.12.2016..
 */
@Component
public class Validators{

    public static String validate(Object object, Validator validator) {

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        String returnString = null;
        final String SEPARATOR = System.getProperty("line.separator");

        if (constraintViolations.size() > 0) {
            //returnString += object.toString() + SEPARATOR;
            returnString = String.format("Кол-во ошибок: %d", constraintViolations.size()) + SEPARATOR;

            for (ConstraintViolation<Object> cv : constraintViolations)
                returnString += String.format(
                        "Внимание, ошибка! [%s], [%s]",
                        cv.getInvalidValue(), cv.getMessage()) + SEPARATOR;
        }

        return returnString;
    }




}
