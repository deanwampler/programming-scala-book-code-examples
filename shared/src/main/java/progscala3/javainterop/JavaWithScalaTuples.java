// src/main/java/progscala3/javainterop/JavaWithScalaTuples.java
package progscala3.javainterop;
import scala.Tuple2;

public class JavaWithScalaTuples {
  public static void main(String[] args) {
    Tuple2<String,Integer> stringInteger = new Tuple2<String,Integer>("one", 2);

    System.out.println(stringInteger);
  }
}