// src/main/java/progscala3/objectsystem/JavaArrays.java
package progscala3.objectsystem;

public class JavaArrays {
  public static void main(String[] args) {
    Integer[] array1 = new Integer[] {
      Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3) };
    Number[] array2 = array1;          // Compiles fine, but shouldn't!!
    array2[2] = Double.valueOf(3.14);  // Compiles, but throws a runtime error!
  }
}
