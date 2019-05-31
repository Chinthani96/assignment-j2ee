package lk.nsbm.shared;

import lk.nsbm.shared.annotations.IdValue;
import lk.nsbm.shared.annotations.NonIdValue;
import lk.nsbm.shared.enums.NonIdType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationFinder {

    public String getIdValueName(Class entityClass) {
        Field[] declaredFields = entityClass.getDeclaredFields();
        for (Field field :
                declaredFields) {
            Annotation[] annotations = field.getAnnotations();

            for (Annotation annotation :
                    annotations) {
                if (annotation.annotationType() == IdValue.class) {
                    return ((IdValue) annotation).name();
                }
            }
        }
        return null;
    }

    public String getNonIdValueName(Class entityClass, NonIdType nonIdType) {
        Field[] declaredFields = entityClass.getDeclaredFields();
        for (Field field :
                declaredFields) {
            Annotation[] annotations = field.getAnnotations();
            System.out.println(field.getName());
            for (Annotation annotation :
                    annotations) {
                System.out.println(annotation.toString());
                if (annotation.annotationType() == NonIdValue.class) {
                    NonIdValue annotated = (NonIdValue) annotation;

                    if (annotated.type().getValue() == nonIdType.getValue()) {
                        return annotated.name();
                    }
                }
            }
        }
        return null;
    }
}
