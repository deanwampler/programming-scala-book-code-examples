// code-examples/TypeSystem/variances/OptionExample.java

package variances;
import java.io.*;
import shapes.*;  // From "Introducing Scala" chapter

public class OptionExample {
  static String[] shapeNames = {"Rectangle", "Circle", "Triangle", "Unknown"};
  static public void main(String[] args) {

    Option<? extends Shape> shapeOption = 
      makeShape(shapeNames[0], new Point(0.,0.), 2., 5.);
    print(shapeNames[0], shapeOption);
    
    shapeOption = makeShape(shapeNames[1], new Point(0.,0.), 2.);
    print(shapeNames[1], shapeOption);

    shapeOption = makeShape(shapeNames[2], 
      new Point(0.,0.), new Point(2.,0.), new Point(0.,2.));
    print(shapeNames[2], shapeOption);

    shapeOption = makeShape(shapeNames[3]);
    print(shapeNames[3], shapeOption);
  }
  
  static public Option<? extends Shape> makeShape(String shapeName, 
      Object... args) {
    if (shapeName == shapeNames[0])
      return new Some<Rectangle>(new Rectangle((Point) args[0], 
        (Double) args[1], (Double) args[2]));
    else if (shapeName == shapeNames[1])
      return new Some<Circle>(new Circle((Point) args[0], (Double) args[1]));
    else if (shapeName == shapeNames[2])
      return new Some<Triangle>(new Triangle((Point) args[0], 
        (Point) args[1], (Point) args[2]));
    else
      return new None<Shape>();
  }
  
  static void print(String name, Option<? extends Shape> shapeOption) {
    System.out.println(name + "? " + shapeOption);
  }
}