// src/test/java/progscala2/javainterop/SMapTest.java
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
import scala.*;
import scala.collection.mutable.LinkedHashMap;

public class SMapTest extends org.scalatest.junit.JUnitSuite {       // <1>
  static class Name {
    public String firstName;
    public String lastName;

    public Name(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName  = lastName;
    }
  }

  LinkedHashMap<Integer, Name> map;

  @Before
  public void setup() {
    map = new LinkedHashMap<Integer, Name>();
    map.update(1, new Name("Dean", "Wampler"));
  }

  @Test
  public void usingMapGetWithOptionName() {                          // <2>
    assertEquals(1, map.size());
    Option<Name> n1 = map.get(1);  // Note: Option<Name>
    assertTrue(n1.isDefined());
    assertEquals("Dean", n1.get().firstName);
  }

  @Test
  public void usingMapGetWithOptionExistential() {                   // <3>
    assertEquals(1, map.size());
    Option<?> n1 = map.get(1);    // Note: Option<?>
    assertTrue(n1.isDefined());
    assertEquals("Dean", ((Name) n1.get()).firstName);
 }
}
