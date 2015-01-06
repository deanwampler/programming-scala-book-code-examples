// src/test/java/progscala2/javainterop/ScalaTuples.java
package progscala2.javainterop;
import scala.Tuple2;

public class ScalaTuples {
  public static void main(String[] args) {
    Tuple2<String,Integer> stringInteger = new Tuple2<String,Integer>("one", 2);

    System.out.println(stringInteger);
  }
}