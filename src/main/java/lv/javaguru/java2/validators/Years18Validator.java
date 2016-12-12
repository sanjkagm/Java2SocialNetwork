package lv.javaguru.java2.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Pavel on 12.12.2016..
 */
public class Years18Validator
        implements ConstraintValidator<Years18, String> {



    public void initialize(Years18 constraintAnnotation) {
    }

    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {

        if ( date == null ) {
            return true;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient( false );
        try {
            Calendar birthDay = Calendar.getInstance();
            birthDay.setTimeInMillis(dateFormat.parse(date).getTime());

            long currentTime = System.currentTimeMillis();
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(currentTime);

            if (now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR) >= 18 )
                return true;
            else
                return false;
        }
        catch (ParseException e) {
            return false;
        }
    }
}