// src/main/scala/progscala2/metaprogramming/Reflect.java

package progscala2.metaprogramming;
import java.util.Arrays;

public class JavaReflect {
  public static abstract class T<A> {
    public A vT;
    public A mT() { return vT; }
  }

  public static class C extends T<String> {
    public String vT = "T";
    public String vC = "C";
    public String mC() { return vC; }
    
    public static class C2 {}
  }

  public static void main(String[] args) {
    C c = new C();

    Class<?> clazz = c.getClass();

    // Methods from java.lang.Class<T>:
    System.out.println("name       = " + clazz.getName());
    System.out.println("methods    = " + Arrays.toString(clazz.getMethods()));
    System.out.println("ctors      = " + Arrays.toString(clazz.getConstructors()));
    System.out.println("fields     = " + Arrays.toString(clazz.getFields()));
    System.out.println("annos      = " + Arrays.toString(clazz.getAnnotations()));
    System.out.println("interfaces = " + Arrays.toString(clazz.getInterfaces()));
    System.out.println("superClass = " + clazz.getSuperclass());
    System.out.println("typeParams = " + Arrays.toString(clazz.getTypeParameters()));
  }
}