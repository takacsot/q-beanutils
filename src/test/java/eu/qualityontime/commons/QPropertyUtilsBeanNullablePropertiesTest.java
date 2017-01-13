package eu.qualityontime.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import junit.framework.TestCase;

public class QPropertyUtilsBeanNullablePropertiesTest extends TestCase {
  QPropertyUtilsBean propertyUtils = new QPropertyUtilsBean();
  TestBean bean = new TestBean();

  public void testMappedGet() throws Exception {
    bean.setMapProperty(null);
    Object
    //
    value = propertyUtils.getMappedProperty(bean, "mapProperty", "First Key");
    assertNull(value);
    value = propertyUtils.getMappedProperty(bean, "mapProperty(First Key)");
    assertNull(value);
    value = propertyUtils.getMappedProperty(bean, "@mapProperty?", "First Key");
    assertNull(value);
    value = propertyUtils.getMappedProperty(bean, "@mapProperty?(First Key)");
    assertNull(value);
  }

  public void testIntexedValue() throws Exception {
    bean.setIntArray(null);
    bean.listIndexed = null;
    for (int i = 0; i < 5; i++) {
      assertNull(propertyUtils.getIndexedProperty(bean, "intArray?", i));
      assertNull(propertyUtils.getIndexedProperty(bean, "@intArray?", i));
      assertNull(propertyUtils.getIndexedProperty(bean, "listIndexed?", i));
      assertNull(propertyUtils.getIndexedProperty(bean, "@listIndexed?", i));

      assertNull(propertyUtils.getIndexedProperty(bean, "intArray?[" + i + "]"));
      assertNull(propertyUtils.getIndexedProperty(bean, "@intArray?[" + i + "]"));
      assertNull(propertyUtils.getIndexedProperty(bean, "listIndexed?[" + i + "]"));
      assertNull(propertyUtils.getIndexedProperty(bean, "@listIndexed?[" + i + "]"));
    }
  }

  public void testGetNested() throws Exception {
    bean.setAnotherNested(null);
    assertNull(propertyUtils.getNestedProperty(bean, "anotherNested?.booleanProperty"));
    assertNull(propertyUtils.getNestedProperty(bean, "@anotherNested?.booleanProperty"));
  }

  public void testGetIndexedArray() {
    bean.int2dArray = null;
    assertNull(propertyUtils.getProperty(bean, "@int2dArray?[0][0]"));
    assertNull(propertyUtils.getProperty(bean, "int2dArray?[0][0]"));
    bean.int2dArray = new int[][] { null, null };
    assertNull(propertyUtils.getProperty(bean, "int2dArray[0]?[0]"));
    assertNull(propertyUtils.getProperty(bean, "@int2dArray[1]?[0]"));
    assertNull(propertyUtils.getProperty(bean, "int2dArray?[0]?[0]"));
  }

  public void testGetIndexedMap() {
    bean = new TestBean((List<Object>) null);
    assertNull(propertyUtils.getProperty(bean, "@listIndexed?[0](FIRST-KEY-1)"));
    assertNull(propertyUtils.getProperty(bean, "listIndexed?[0](FIRST-KEY-1)"));
    List<Object> mainList = new ArrayList<Object>();
    mainList.add(null);
    mainList.add(null);
    bean = new TestBean(mainList);
    assertNull(propertyUtils.getProperty(bean, "@listIndexed[0]?(FIRST-KEY-1)"));
    assertNull(propertyUtils.getProperty(bean, "listIndexed[0]?(FIRST-KEY-1)"));
  }

  public void testGetMappedArray() {
    bean.setMapProperty(null);
    assertNull(propertyUtils.getProperty(bean, "mapProperty?(mappedArray)[0]"));
    assertNull(propertyUtils.getProperty(bean, "@mapProperty?(mappedArray)[1]"));
    bean.setMapProperty(new HashMap<String, Object>());
    assertNull(propertyUtils.getProperty(bean, "mapProperty(mappedArray)?[0]"));
    assertNull(propertyUtils.getProperty(bean, "@mapProperty(mappedArray)?[1]"));
  }

  public void testGetMappedMap() {
    bean.setMapProperty(null);
    assertNull(propertyUtils.getProperty(bean, "mapProperty?(mappedMap)(sub-key-1)"));
    bean.setMapProperty(new HashMap<String, Object>());
    assertNull(propertyUtils.getProperty(bean, "mapProperty(mappedMap)?(sub-key-1)"));
  }
  //indexedList of lists as previous
}
