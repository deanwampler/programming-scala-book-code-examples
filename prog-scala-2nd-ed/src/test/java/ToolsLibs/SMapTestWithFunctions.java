// code-examples/ToolsLibs/SMapTestWithFunctions.java

import org.junit.*;
import static org.junit.Assert.*;
import scala.*;
import scala.collection.mutable.LinkedHashMap;
import static scala.collection.Map.Projection;

public class SMapTestWithFunctions {
  static class Name {
    public String firstName;
    public String lastName;
    
    public Name(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName  = lastName;
    }
    
    public static Name emptyName = new Name("","");
    
    public static Function0<Name> empty = new Function0<Name>() { 
      public Name apply() { return emptyName; }
      
      public int $tag() { return 0; }
    };
  }
  
  LinkedHashMap<Integer, Name> map;
  
  @Before
  public void setup() {
    map = new LinkedHashMap<Integer, Name>();
    map.update(1, new Name("Dean", "Wampler"));
    map.update(2, new Name("Alex", "Payne"));        
  }
  
  @Test
  public void usingMapGetOrElse() {
    assertEquals(2, map.size());
    assertEquals("Dean", ((Name) map.getOrElse(1, Name.empty)).firstName);
    assertEquals("Alex", ((Name) map.getOrElse(2, Name.empty)).firstName);
  }

  Function1<Integer, Boolean> filter = new Function1<Integer, Boolean>() {
    public Boolean apply(Integer i) { return i.intValue() <= 1; }
    
    public <A> Function1<A,Boolean> compose(Function1<A,Integer> g) { 
      return Function1$class.compose(this, g);
    }
    
    public <A> Function1<Integer,A> andThen(Function1<Boolean,A> g) { 
      return Function1$class.andThen(this, g);
    }

    public int $tag() { return 0; }
  };

  @Test
  public void usingFilterKeys() {
    assertEquals(2, map.size());
    Projection<Integer, Name> filteredMap = 
        (Projection<Integer, Name>) map.filterKeys(filter);
    assertEquals(1, filteredMap.size());
    assertEquals("Dean", filteredMap.getOrElse(1, Name.empty).firstName);
    assertEquals("",     filteredMap.getOrElse(2, Name.empty).firstName);
  }
}