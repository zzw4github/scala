package annotation;

import java.lang.annotation.*;

/**
 * Created by infosea on 2016-08-17.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Import {
    String value() default "";
}
