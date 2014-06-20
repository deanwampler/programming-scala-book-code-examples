// src/test/java/JavaInterop/ScalaTuples.java

package javainterop;

import scala.Tuple2;

public class ScalaTuples {
  public static void main(String[] args) {
    Tuple2 stringInteger = new Tuple2<String,Integer>("one", 2);

    System.out.println(stringInteger);
  }
}