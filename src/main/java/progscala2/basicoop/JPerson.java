// src/main/java/progscala2/basicoop/JPerson.java
package progscala2.basicoop;

public class JPerson {
  private String name;
  private int    age;

  public JPerson(String name, int age) {
    this.name = name;
    this.age  = age;
  }

  public void   setName(String name) { this.name = name; }
  public String getName()            { return this.name; }

  public void setAge(int age) { this.age = age;  }
  public int  getAge()        { return this.age; }
}
