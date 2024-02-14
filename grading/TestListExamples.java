import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void filterKeepsOrder() {
      List<String> in = Arrays.asList("Yes", "", "sigh", "and", "andp", "was");
      List<String> out = Arrays.asList("Yes","sigh", "was");
      assertEquals(out, ListExamples.filter(in, (String s) -> s.contains("s")));
  }
  @Test(timeout = 500)
  public void filterTwice() {
      List<String> in = Arrays.asList("Yes", "", "sigh", "and", "andp", "was");
      List<String> actual1 = ListExamples.filter(in, (String s) -> s.contains("s"));
      assertEquals(Arrays.asList("Yes","sigh", "was"), actual1);
      in = Arrays.asList("r", "s", "t");
      assertEquals(Arrays.asList("s"), ListExamples.filter(in, (String s) -> s.contains("s")));
      assertEquals(Arrays.asList("Yes","sigh", "was"), actual1);
  }
  
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMerge() {
      List<String> a = Arrays.asList("a", "d", "x");
      List<String> b = Arrays.asList("b", "c");
      List<String> out = Arrays.asList("a","b","c","d","x");
      assertEquals(out, ListExamples.merge(a,b));
      assertEquals(out, ListExamples.merge(b,a));
  }

  @Test(timeout = 500)
  public void testMergeAllInOne() {
      List<String> a = Arrays.asList("a", "d", "x");
      List<String> b = Arrays.asList();
      List<String> out = Arrays.asList("a", "d", "x");
      assertEquals(out, ListExamples.merge(a,b));
      assertEquals(out, ListExamples.merge(b,a));
  }
}
