package eu.qualityontime.commons;

import java.lang.reflect.Field;

class FieldUtils {

  static Field findField(final Class<?> currentClass, final String fieldName) {

    if (null == currentClass) {
      return null;
    }
    for (final Field cf : currentClass.getDeclaredFields()) {
      if (fieldName.equals(cf.getName())) {
        return cf;
      }
    }
    return findField(currentClass.getSuperclass(), fieldName);
  }

  public static Object readField(Object bean, String fieldName)
      throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    final Field f = findField(bean.getClass(), fieldName);
    if (null == f) {
      throw new NoSuchFieldException("field `" + fieldName + "` not found");
    }
    f.setAccessible(true);
    return f.get(bean);
  }

  public static void writeField(Object bean, String fieldName, Object val)
      throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
    final Field f = findField(bean.getClass(), fieldName);
    if (null == f) {
      throw new NoSuchFieldException("field `" + fieldName + "` not found");
    }
    f.setAccessible(true);
    f.set(bean, val);
  }

}
