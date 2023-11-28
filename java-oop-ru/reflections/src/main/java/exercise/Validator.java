package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {
    public static List<String> validate(exercise.Address address) {
        List<String> invalidFields = new ArrayList<>();
        try {
            Field[] fieldArray = address.getClass().getDeclaredFields();
            for (Field field : fieldArray) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(exercise.NotNull.class) && Objects.equals(field.get(address), null)) {
                    invalidFields.add(field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return invalidFields;
    }

    public static Map<String, List<String>> advancedValidate(exercise.Address address) {
        Map<String, List<String>> invalidFields = new HashMap<>();
        String notNullError = "can not be null";
        String minLengthError = "length less than 4";
        try {
            Field[] fieldArray = address.getClass().getDeclaredFields();
            for (Field field : fieldArray) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(exercise.NotNull.class) && Objects.equals(field.get(address), null)) {
                    if (field.isAnnotationPresent(exercise.MinLength.class)) {
                        invalidFields.put(field.getName(), List.of(notNullError, minLengthError));
                    } else {
                        invalidFields.put(field.getName(), List.of(notNullError));
                    }
                } else if (field.isAnnotationPresent(exercise.MinLength.class)) {
                    String str = (String) field.get(address);
                    if (str.length() < field.getDeclaredAnnotation(exercise.MinLength.class).minLength()) {
                        invalidFields.put(field.getName(), List.of(minLengthError));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return invalidFields;

    }
}
// END
