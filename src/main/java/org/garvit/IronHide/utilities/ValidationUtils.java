package org.garvit.IronHide.utilities;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

import jakarta.validation.*;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Validation Utility Class: All Validations of request and objects will be done here.
 *
 * <p>This utility class is designed to provide common validation functionality, including
 * precondition checks and Jakarta Bean Validation.
 *
 * @author garvit-joshi on 12/11/23
 */
public class ValidationUtils {

  private static final Validator VALIDATOR;

  static {
    try (final ValidatorFactory factory = buildDefaultValidatorFactory()) {
      VALIDATOR = factory.getValidator();
    }
  }

  private ValidationUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Checks a precondition expression and throws a ValidationException with a formatted message if
   * the expression evaluates to true.
   *
   * @param expression The boolean expression to be checked.
   * @param template A template for the exception message if the check fails.
   * @param args Arguments to format the message using {@link String#format(String, Object...)}.
   * @throws ValidationException if the expression is true.
   */
  public static void checkPrecondition(boolean expression, String template, Object... args) {
    if (expression) {
      throw new ValidationException(String.format(Locale.US, template, args));
    }
  }

  /**
   * Processes a set of constraint violations and throws a ValidationException with a detailed error
   * message listing all the violations.
   *
   * @param <T> Type of the validated object.
   * @param violations Set of constraint violations.
   * @throws ValidationException with a detailed list of violations.
   */
  private static <T> void throwValidationException(Set<ConstraintViolation<T>> violations) {
    int violationIndex = 0;
    final StringJoiner joiner =
        new StringJoiner(System.lineSeparator(), "", System.lineSeparator());
    for (ConstraintViolation<T> violation : violations) {
      String s =
          violationIndex++ + ". " + violation.getPropertyPath() + ": " + violation.getMessage();
      joiner.add(s);
    }
    final String errorMessage = joiner.toString();

    throw new ValidationException(errorMessage);
  }

  /**
   * Validates an object based on Jakarta Bean Validation constraints. If the object is found to
   * have constraint violations, a detailed ValidationException is thrown.
   *
   * @param <T> Type of the validated object.
   * @param object The object to be validated.
   * @param checkNullable Flag to decide if the method should throw an exception if the object is
   *     null.
   * @throws ValidationException if there are constraint violations or if the object is null when
   *     checkNullable is true.
   */
  public static <T> void validateObject(T object, boolean checkNullable) {
    if (checkNullable) {
      checkPrecondition(Objects.isNull(object), "Object Cannot be null");
    } else if (Objects.isNull(object)) {
      return;
    }

    final Set<ConstraintViolation<T>> violations = VALIDATOR.validate(object);

    if (!violations.isEmpty()) {
      throwValidationException(violations);
    }
  }
}
