// src/test/java/progscala3/javainterop/ScalaTuples.java
package progscala3.javainterop;
import scala.Tuple2;

public class ScalaTuples {
  public static void main(String[] args) {
    Tuple2<String,Integer> stringInteger = new Tuple2<String,Integer>("one", 2);

    System.out.println(stringInteger);
  }
}