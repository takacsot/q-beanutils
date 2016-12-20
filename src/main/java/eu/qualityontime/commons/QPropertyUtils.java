package eu.qualityontime.commons;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class QPropertyUtils {
	private static QPropertyUtilsBean propertyUtils = new QPropertyUtilsBean();

	public static void clearDescriptors() {
		try {
			propertyUtils.clearDescriptors();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T copyProperties(final T dest, final Object orig) {
		try {
			propertyUtils.copyProperties(dest, orig);
			return dest;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Map<String, Object> describe(final Object bean) {
		try {
			return cast(propertyUtils.describe(bean));
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object getProperty(final Object bean, final String name) {
		try {
			return propertyUtils.getProperty(bean, name);
		} catch (final NestedNullException e) {
			return null;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T getProperty(final Object bean, final String name, final Class<T> clazz) {
		try {
			return cast(propertyUtils.getProperty(bean, name));
		} catch (final NestedNullException e) {
			return null;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static PropertyDescriptor getPropertyDescriptor(final Object bean, final String name) {
		try {
			return propertyUtils.getPropertyDescriptor(bean, name);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static PropertyDescriptor[] getPropertyDescriptors(final Class beanClass) {
		try {
			return propertyUtils.getPropertyDescriptors(beanClass);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static PropertyDescriptor[] getPropertyDescriptors(final Object bean) {
		try {
			return propertyUtils.getPropertyDescriptors(bean);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Class getPropertyEditorClass(final Object bean, final String name) {
		try {
			return propertyUtils.getPropertyEditorClass(bean, name);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Class getPropertyType(final Object bean, final String name) {
		try {
			return propertyUtils.getPropertyType(bean, name);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Method getReadMethod(final PropertyDescriptor descriptor) {
		try {
			return propertyUtils.getReadMethod(descriptor);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Method getWriteMethod(final PropertyDescriptor descriptor) {
		try {
			return propertyUtils.getWriteMethod(descriptor);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isReadable(final Object bean, final String name) {
		try {
			return propertyUtils.isReadable(bean, name);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isWriteable(final Object bean, final String name) {
		try {
			return propertyUtils.isWriteable(bean, name);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setProperty(final Object bean, final String name, final Object value) {
		try {
			propertyUtils.setProperty(bean, name, value);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void set(final Object bean, final String name, final Object value) {
		try {
			propertyUtils.setProperty(bean, name, value);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verifies whether the param Map contains the same or less keys as the
	 * target object properties. Not verifying property values compatibility.
	 * Imagine you have class: public static class A { String apple = "alma"; B
	 * b = null; } and a Map of ImmutableMap.of("b", "he", "apple",
	 * "cuncimokus") It will return TRUE because both `b` and `apple` is valida
	 * attrobute in class A even if the type of `b` is different. Map of
	 * `ImmutableMap.of("b", "he")` is also true because b is in list of
	 * propeties of A. But `ImmutableMap.of("b", "he", "cccc", "c")` will fail
	 * because `cccc` is not a property of class A.
	 */
	/*
	 * public static boolean verifyPropertiesPresented(Object target,
	 * Map<String, ?> params) { Preconditions.checkNotNull(target);
	 * Preconditions.checkNotNull(params); return
	 * verifyPropertiesPresented(target, params.keySet()); }
	 *
	 * public static boolean verifyPropertiesPresented(Object target,
	 * Set<String> props) { Preconditions.checkNotNull(target);
	 * Preconditions.checkNotNull(props); Map<String, Object> propMap =
	 * Maps.filterKeys(JakartaPropertyUtils.describe(target),
	 * not(equalTo("class"))); Set<String> propKeys = propMap.keySet(); return
	 * propKeys.containsAll(props); }
	 */

	@SuppressWarnings("unchecked")
	// at your own risk
	private static <T> T cast(final Object o) {
		return (T) o;
	}

}
