package dmg.base_authorization.util.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String first;
    private String second;
    private String message;

    public void initialize(FieldMatch constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.first = constraintAnnotation.first();
        this.second = constraintAnnotation.second();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean matches = false;

        try {
            final Object firstObject = getProperty(value, first);
            final Object secondObject = getProperty(value, second);

            matches = isFoundMatches(firstObject, secondObject);

            if (!matches) {
                setMessage(context);
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }

        return matches;
    }

    private Object getProperty(Object value, String fieldName) {
        Class<?> clazz = value.getClass();
        String methodName = getMethodName(fieldName);
        try {
            Method method = clazz.getDeclaredMethod(methodName);
            return method.invoke(value);
        } catch (Exception ignored) {
        }
        return null;
    }

    private void setMessage(ConstraintValidatorContext context) {
        String msg = this.message;
        if (this.message == null || "".equals(this.message) || "fields.notMatches".equals(this.message)) {
            msg = first + " is not equal to " + second;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg).addNode(second).addConstraintViolation();
    }

    private boolean isFoundMatches(Object firstObject, Object secondObject) {
        boolean matches = false;
        if ((firstObject == null && secondObject == null) || (firstObject != null && firstObject.equals(secondObject))) {
            matches = true;
        }
        return matches;
    }

    private String getMethodName(String fieldName) {
        return "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    }
}
