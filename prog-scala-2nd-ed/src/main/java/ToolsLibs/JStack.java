// code-examples/ToolsLibs/JStack.java

import java.util.*;

public class JStack<T> {
  private List<T> stack = new ArrayList<T>();
  public void push(T t) {
    stack.add(t);
  }
  public T pop() {
    return stack.remove(stack.size() - 1);
  }
}