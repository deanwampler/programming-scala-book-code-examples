// src/main/scala/BasicOOP/Person.java

package BasicOOP;

public class Person {
  private String name;
  private int    age;

  public Person(String name, int age) {
    this.name = name;
    this.age  = age;
  }

  public void   setName(String name) { this.name = name; }
  public String getName()            { return this.name; }

  public void setAge(int age) { this.age = age;  }
  public int  getAge()        { return this.age; }
}
