// code-examples/AppDesign/annotations/Pre.java

package org.contract4j5.contract;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Pre {
  /**
   * The "value" is the test expression, which must evaluate to true or false.
   * It must be a valid expression in the scripting language you are using.
   */
  String value() default "";

  /**
   * An optional message to print with the standard message when the contract
   * fails.
   */
  String message() default "";
}
