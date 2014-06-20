// src/test/java/javaInterop/SMapTest.java

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
import scala.*;
import scala.collection.mutable.LinkedHashMap;

/**
 * NOTE: You must extend this ScalaTest-specific JUnitSuite for ScalaTest to
 * find the test.
 */
public class SMapTest extends org.scalatest.junit.JUnitSuite {
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
    map.update(2, new Name("Alex", "Payne"));        
  }
  
  @Test
  public void usingMapGetWithOptionName() {
    assertEquals(2, map.size());
    Option<Name> n1 = map.get(1);  // Note: Option<Name>
    Option<Name> n2 = map.get(2);  // Note: Option<Name>
    assertTrue(n1.isDefined());
    assertTrue(n2.isDefined());
    assertEquals("Dean", n1.get().firstName);
    assertEquals("Alex", n2.get().firstName);
  }
  
  @Test
  public void usingMapGetWithOptionExistential() {
    assertEquals(2, map.size());
    Option<?> n1 = map.get(1);    // Note: Option<?>
    Option<?> n2 = map.get(2);    // Note: Option<?>
    assertTrue(n1.isDefined());
    assertTrue(n2.isDefined());
    assertEquals("Dean", ((Name) n1.get()).firstName);
    assertEquals("Alex", ((Name) n2.get()).firstName);
 }
}
