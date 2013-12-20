// code-examples/TypeSystem/variances/Some.java

package variances;

public class Some<T> extends Option<T> {

  public Some(T value) {
    this.value = value;
  }
  
  public boolean isEmpty() { return false; }
  
  private T value;
  
  public T get() { return value; }
  
  public String toString() {
    return "Option(" + value + ")";
  }
}