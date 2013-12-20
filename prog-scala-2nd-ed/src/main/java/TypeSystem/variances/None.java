// code-examples/TypeSystem/variances/None.java

package variances;

public class None<T> extends Option<T> {

  public boolean isEmpty() { return true; }
  
  public T get() { throw new java.util.NoSuchElementException(); }
  
  public String toString() {
    return "None";
  }
}