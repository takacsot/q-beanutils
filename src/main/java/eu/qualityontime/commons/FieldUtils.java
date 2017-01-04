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

}
