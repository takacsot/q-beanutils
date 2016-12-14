package eu.qualityontime.commons;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class QPropertyUtilsTest {

  @Test
  public void semi_elvis() {
    A a = new A();
    assertEquals("alma", QPropertyUtils.getProperty(a, "apple"));
    assertNull(QPropertyUtils.getProperty(a, "b"));
    assertNull(QPropertyUtils.getProperty(a, "b.korte"));
    a.setB(new B());
    assertEquals("korte", QPropertyUtils.getProperty(a, "b.korte"));
  }

  public static class A {
    String apple = "alma";
    B b = null;

    public String getApple() {
      return apple;
    }

    public void setApple(String apple) {
      this.apple = apple;
    }

    public B getB() {
      return b;
    }

    public void setB(B b) {
      this.b = b;
    }
  }

  public static class B {
    String korte = "korte";

    public String getKorte() {
      return korte;
    }

    public void setKorte(String korte) {
      this.korte = korte;
    }
  }

  /*@Test
  public void TestVerifyPropertiesPresented() throws Exception {
    A a = new A();
    assertTrue(QPropertyUtils.verifyPropertiesPresented(a, ImmutableMap.of("b", "he", "apple", "cuncimokus")));
    assertTrue(QPropertyUtils.verifyPropertiesPresented(a, ImmutableMap.of("b", "he")));
    assertFalse(QPropertyUtils.verifyPropertiesPresented(a,
        ImmutableMap.of("b", "he", "apple", "cuncimokus", "korte", "k")));
  }*/
}
