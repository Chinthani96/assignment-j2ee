package lk.nsbm.shared.annotations;

import lk.nsbm.shared.enums.NonIdType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NonIdValue {

    NonIdType type();

    String name();

}
