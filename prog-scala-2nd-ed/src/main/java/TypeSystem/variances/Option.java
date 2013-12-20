// code-examples/TypeSystem/variances/Option.java

package variances;

abstract public class Option<T> {
  abstract public boolean isEmpty();
  
  abstract public T get();
  
  public T getOrElse(T t) {
    return isEmpty() ? t : get();
  }
}