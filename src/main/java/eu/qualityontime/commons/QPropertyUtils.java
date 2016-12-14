package eu.qualityontime.commons;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.commons.beanutils.*;


@SuppressWarnings("rawtypes")
public class QPropertyUtils {
  public static void clearDescriptors() {
    try {
      PropertyUtils.clearDescriptors();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T copyProperties(T dest, Object orig) {
    try {
      PropertyUtils.copyProperties(dest, orig);
      return dest;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Map<String, Object> describe(Object bean) {
    try {
      return cast(PropertyUtils.describe(bean));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Object getProperty(Object bean, String name) {
    try {
      return PropertyUtils.getProperty(bean, name);
    } catch (NestedNullException e) {
      return null;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T getProperty(Object bean, String name, Class<T> clazz) {
    try {
      return cast(PropertyUtils.getProperty(bean, name));
    } catch (NestedNullException e) {
      return null;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static PropertyDescriptor getPropertyDescriptor(Object bean, String name) {
    try {
      return PropertyUtils.getPropertyDescriptor(bean, name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static PropertyDescriptor[] getPropertyDescriptors(Class beanClass) {
    try {
      return PropertyUtils.getPropertyDescriptors(beanClass);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static PropertyDescriptor[] getPropertyDescriptors(Object bean) {
    try {
      return PropertyUtils.getPropertyDescriptors(bean);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Class getPropertyEditorClass(Object bean, String name) {
    try {
      return PropertyUtils.getPropertyEditorClass(bean, name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Class getPropertyType(Object bean, String name) {
    try {
      return PropertyUtils.getPropertyType(bean, name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Method getReadMethod(PropertyDescriptor descriptor) {
    try {
      return PropertyUtils.getReadMethod(descriptor);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Method getWriteMethod(PropertyDescriptor descriptor) {
    try {
      return PropertyUtils.getWriteMethod(descriptor);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean isReadable(Object bean, String name) {
    try {
      return PropertyUtils.isReadable(bean, name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean isWriteable(Object bean, String name) {
    try {
      return PropertyUtils.isWriteable(bean, name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void setProperty(Object bean, String name, Object value) {
    try {
      PropertyUtils.setProperty(bean, name, value);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  public static void set(Object bean, String name, Object value) {
    try {
      PropertyUtils.setProperty(bean, name, value);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Verifies whether the param Map contains the same or less keys as the target object properties.
   * Not verifying property values compatibility.
   * Imagine you have class:
   * public static class A {
   * String apple = "alma";
   * B b = null;
   * }
   * and a Map of
   * ImmutableMap.of("b", "he", "apple", "cuncimokus")
   * It will return TRUE because both `b` and `apple` is valida attrobute in class A even if
   * the type of `b` is different.
   * Map of `ImmutableMap.of("b", "he")` is also true because b is in list of propeties of A.
   * But `ImmutableMap.of("b", "he", "cccc", "c")` will fail because `cccc` is not a property of class A.
   */
  /*public static boolean verifyPropertiesPresented(Object target, Map<String, ?> params) {
    Preconditions.checkNotNull(target);
    Preconditions.checkNotNull(params);
    return verifyPropertiesPresented(target, params.keySet());
  }

  public static boolean verifyPropertiesPresented(Object target, Set<String> props) {
    Preconditions.checkNotNull(target);
    Preconditions.checkNotNull(props);
    Map<String, Object> propMap = Maps.filterKeys(JakartaPropertyUtils.describe(target), not(equalTo("class")));
    Set<String> propKeys = propMap.keySet();
    return propKeys.containsAll(props);
  }*/


  @SuppressWarnings("unchecked")
  // at your own risk
  private static <T> T cast(Object o) {
    return (T) o;
  }

}
