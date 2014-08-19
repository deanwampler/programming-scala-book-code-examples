// src/main/java/progscala2/typesystem/variances/OptionExample.java
package progscala2.typesystem.variances;
import java.io.*;
import progscala2.introscala.shapes.*;

public class OptionExample {
  static String[] shapeNames = {"Rectangle", "Circle", "Triangle", "Unknown"};

  static class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(String message) {
      super(message);
    }
  }

  static public void main(String[] args) {

    Option<? extends Shape> shapeOption =
      makeShape(shapeNames[0], new Point(0.0, 0.0), 2.0, 5.0);
    print(shapeNames[0], shapeOption);

    shapeOption = makeShape(shapeNames[1], new Point(0.0, 0.0), 2.0);
    print(shapeNames[1], shapeOption);

    shapeOption = makeShape(shapeNames[2],
      new Point(0.0, 0.0), new Point(2.0, 0.0), new Point(0.0, 2.0));
    print(shapeNames[2], shapeOption);

    shapeOption = makeShape(shapeNames[3]);
    print(shapeNames[3], shapeOption);
  }

  static private void checkArgs(int expected, Object args[]) {
    if (args.length != expected) {
        throw new InvalidArgumentsException(
          String.format("Expected %d arguments. Got %d: %s",
            expected, args.length, args.toString()));
    }
  }

  static public Option<? extends Shape> makeShape(String shapeName,
      Object... args) {
    if (shapeName == shapeNames[0]) {
      checkArgs (3, args);
      return new Some<Rectangle>(new Rectangle((Point) args[0],
        (Double) args[1], (Double) args[2]));
    }
    else if (shapeName == shapeNames[1]) {
      checkArgs (2, args);
      return new Some<Circle>(new Circle((Point) args[0], (Double) args[1]));
    }
    else if (shapeName == shapeNames[2]) {
      checkArgs (3, args);
      return new Some<Triangle>(new Triangle((Point) args[0],
        (Point) args[1], (Point) args[2]));
    }
    else
      return new None<Shape>();
  }

  static void print(String name, Option<? extends Shape> shapeOption) {
    System.out.println(name + "? " + shapeOption);
  }
}
