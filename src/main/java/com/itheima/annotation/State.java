package com.itheima.annotation;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    String message() default "{发布状态只有已发布和草稿两种}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
