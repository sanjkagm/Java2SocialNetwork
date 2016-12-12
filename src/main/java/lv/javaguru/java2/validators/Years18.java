package lv.javaguru.java2.validators;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
/**
 * Created by Pavel on 12.12.2016..
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = Years18Validator.class)
@Documented
public @interface Years18 {

    String message() default "{lv.javaguru.java2.validators.Years18." +
            "message}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
