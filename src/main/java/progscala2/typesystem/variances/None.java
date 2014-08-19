// src/main/java/progscala2/typesystem/variances/None.java
package progscala2.typesystem.variances;

public class None<T> extends Option<T> {

  public boolean isEmpty() { return true; }

  public T get() { throw new java.util.NoSuchElementException(); }

  public String toString() {
    return "None";
  }
}
