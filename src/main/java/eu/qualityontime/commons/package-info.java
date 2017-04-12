/**
 * <p>The <em>Bean Introspection Utilities</em> component of the Apache Commons
 * subproject offers low-level utility classes that assist in getting and setting
 * property values on Java classes that follow the naming design patterns outlined
 * in the JavaBeans Specification, as well as mechanisms for dynamically defining
 * and accessing bean properties.</p>
 *
 * <h1>Table of Contents</h1>
 *
 * <ul>
 * <li>1. <a href="#overview">Overview</a>
 *     <ul>
 *     <li>1.1 <a href="#overview.background">Background</a></li>
 *     <li>1.2 <a href="#overview.dependencies">External Dependencies</a></li>
 *     </ul>
 * </li>
 * <li>2. <a href="#standard">Standard JavaBeans</a>
 *     <ul>
 *     <li>2.1 <a href="#standard.background">Background</a></li>
 *     <li>2.2 <a href="#standard.basic">Basic Property Access</a></li>
 *     <li>2.3 <a href="#standard.nested">Nested Property Access</a></li>
 *     <li>2.4 <a href="#standard.customize">Customizing Introspection</a></li>
 *     <li>2.5 <a href="#standard.suppress">Suppressing Properties</a></li>
 *     </ul>
 * </li>
 * <li>3. <a href="#dynamic">Dynamic Beans (DynaBeans)</a>
 *     <ul>
 *     <li>3.1 <a href="#dynamic.background">Background</a></li>
 *     <li>3.2 <a href="#dynamic.basic">BasicDynaBean and BasicDynaClass</a></li>
 *     <li>3.3 <a href="#dynamic.resultSet">ResultSetDynaClass (Wraps ResultSet in DynaBeans)</a></li>
 *     <li>3.4 <a href="#dynamic.rowSet">RowSetDynaClass (Disconnected ResultSet as DynaBeans)</a></li>
 *     <li>3.5 <a href="#dynamic.wrap">WrapDynaBean and WrapDynaClass</a></li>
 *     <li>3.6 <a href="#dynamic.lazy"><i>Lazy</i> DynaBeans</a></li>
 *     </ul>
 * </li>
 * <li>4. <a href="#conversion">Data Type Conversions</a>
 *     <ul>
 *     <li>4.1 <a href="#conversion.background">Background</a></li>
 *     <li>4.2 <a href="#conversion.beanutils">BeanUtils and ConvertUtils
 *         Conversions</a></li>
 *     <li>4.3 <a href="#conversion.defining">Defining Your Own Converters</a></li>
 *     <li>4.4 <a href="#conversion.i18n">Locale Aware Conversions</a></li>
 *     </ul>
 * </li>
 * <li>5. <a href="#instances">Utility Objects And Static Utility Classes</a></li>
 * <li>6. <a href="#collections">Collections</a>
 *     <ul>
 *     <li>6.1 <a href="#bean-comparator">Comparing Beans</a></li>
 *     <li>6.2 <a href="#bean-property-closure">Operating On Collections Of Beans</a></li>
 *     <li>6.3 <a href="#bean-property-predicate">Querying Or Filtering Collections Of Beans</a></li>
 *     <li>6.4 <a href="#bean-property-transformer">Transforming Collections Of Beans</a></li>
 *     </ul></li>
 * <li>7. <a href="#FAQ">Frequently Asked Questions</a>
 *     <ul>
 *     <li><a href="#FAQ.property">Why Can't BeanUtils Find My Method?</a></li>
 *     <li><a href="#FAQ.bc.order">How Do I Set The BeanComparator Order To Be Ascending/Descending?</a></li>
 *     </ul></li>
 * </ul>
 *
 * <a name="overview"></a>
 * <h1>1. Overview</h1>
 *
 * <a name="overview.background"></a>
 * <h2>1.1 Background</h2>
 *
 * <p>The <em>JavaBeans</em> name comes from a
 * <a href="http://java.sun.com/products/javabeans/">Java API</a>
 * for a component architecture for the Java language.  Writing Java classes that
 * conform to the JavaBeans design patterns makes it easier for Java developers
 * to understand the functionality provided by your class, as well as allowing
 * JavaBeans-aware tools to use Java's <em>introspection</em> capabilities to
 * learn about the properties and operations provided by your class, and present
 * them in a visually appealing manner in development tools.</p>
 *
 * <p>The <a href="http://java.sun.com/products/javabeans/docs/spec.html">JavaBeans
 * Specification</a> describes the complete set of characteristics that makes
 * an arbitrary Java class a JavaBean or not -- and you should consider reading
 * this document to be an important part of developing your Java programming
 * skills.  However, the required characteristics of JavaBeans that are
 * important for most development scenarios are listed here:</p>
 * <ul>
 * <li>The class must be <strong>public</strong>, and provide a
 *     <strong>public</strong> constructor that accepts no arguments.  This allows
 *     tools and applications to dynamically create new instances of your bean,
 *     without necessarily knowing what Java class name will be used ahead of
 *     time, like this:
 * <pre>
 *         String className = ...;
 *         Class beanClass = Class.forName(className);
 *         Object beanInstance = beanClass.newInstance();
 * </pre></li>
 * <li>As a necessary consequence of having a no-arguments constructor,
 *     configuration of your bean's behavior must be accomplished separately
 *     from its instantiation.  This is typically done by defining a set of
 *     <em>properties</em> of your bean, which can be used to modify its behavior
 *     or the data that the bean represents.  The normal convention for
 *     property names is that they start with a lower case letter, and be
 *     comprised only of characters that are legal in a Java identifier.</li>
 * <li>Typically, each bean property will have a public <em>getter</em> and
 *     <em>setter</em> method that are used to retrieve or define the property's
 *     value, respectively.  The JavaBeans Specification defines a design
 *     pattern for these names, using <code>get</code> or <code>set</code> as the
 *     prefix for the property name with it's first character capitalized.  Thus,
 *     you a JavaBean representing an employee might have
 *     (among others) properties named <code>firstName</code>,
 *     <code>lastName</code>, and <code>hireDate</code>, with method signatures
 *     like this:
 * <pre>
 *         public class Employee {
 *             public Employee();   // Zero-arguments constructor
 *             public String getFirstName();
 *             public void setFirstName(String firstName);
 *             public String getLastName();
 *             public void setLastName(String lastName);
 *             public Date getHireDate();
 *             public void setHireDate(Date hireDate);
 *             public boolean isManager();
 *             public void setManager(boolean manager);
 *             public String getFullName();
 *         }
 * </pre></li>
 * <li>As you can see from the above example, there is a special variant allowed
 *     for boolean properties -- you can name the <em>getter</em> method with a
 *     <code>is</code> prefix instead of a <code>get</code> prefix if that makes
 *     for a more understandable method name.</li>
 * <li>If you have both a <em>getter</em> and a <em>setter</em> method for a
 *     property, the data type returned by the <em>getter</em> must match the
 *     data type accepted by the <em>setter</em>.  In addition, it is contrary
 *     to the JavaBeans specification to have more than one <em>setter</em>
 *     with the same name, but different property types.</li>
 * <li>It is not required that you provide a <em>getter</em> and a
 *     <em>setter</em> for every property.  In the example above, the
 *     <code>fullName</code> property is read-only, because there is no
 *     <em>setter</em> method.  It is also possible, but less common, to provide
 *     write-only properties.</li>
 * <li>It is also possible to create a JavaBean where the <em>getter</em> and
 *     <em>setter</em> methods do not match the naming pattern described above.
 *     The standard JavaBeans support classes in the Java language, as well as
 *     all classes in the BeanUtils package, allow you to describe the actual
 *     property method names in a <code>BeanInfo</code> class associated with
 *     your bean class.  See the JavaBeans Specification for full details.</li>
 * <li>The JavaBeans Specification also describes many additional design patterns
 *     for event listeners, wiring JavaBeans together into component hierarchies,
 *     and other useful features that are beyond the scope of the BeanUtils
 *     package.</li>
 * </ul>
 *
 * <p>Using standard Java coding techniques, it is very easy to deal with
 * JavaBeans if you know ahead of time which bean classes you will be using, and
 * which properties you are interested in:</p>
 * <pre>
 *         Employee employee = ...;
 *         System.out.println("Hello " + employee.getFirstName() + "!");
 * </pre>
 *
 * <a name="overview.dependencies"></a>
 * <h2>1.2 External Dependencies</h2>
 *
 * <p>The <em>commons-beanutils</em> package requires that the following
 * additional packages be available in the application's class path at runtime:
 * </p>
 * <ul>
 * <li><a href="http://commons.apache.org/downloads/download_logging.cgi">
 * Logging Package (Apache Commons)</a>, version 1.0 or later</li>
 * <li><a href="http://commons.apache.org/downloads/download_collections.cgi">
 * Collections Package (Apache Commons)</a>, version 1.0 or later</li>
 * </ul>
 *
 * <a name="standard"></a>
 * <h1>2. Standard JavaBeans</h1>
 *
 * <a name="standard.background"></a>
 * <h2>2.1 Background</h2>
 *
 * <p>As described above, the standard facilities of the Java programming language
 * make it easy and natural to access the property values of your beans using
 * calls to the appropriate getter methods.
 * But what happens in more sophisticated environments where you do not
 * necessarily know ahead of time which bean class you are going to be using,
 * or which property you want to retrieve or modify?  The Java language provides
 * classes like <code>java.beans.Introspector</code>, which can examine a Java
 * class at runtime and identify for you the names of the property getter and
 * setter methods, plus the <em>Reflection</em> capabilities to dynamically call
 * such a method.  However, these APIs can be difficult to use, and expose the
 * application developer to many unnecessary details of the underlying structure
 * of Java classes.  The APIs in the BeanUtils package are intended to simplify
 * getting and setting bean properties dynamically, where the objects you are
 * accessing -- and the names of the properties you care about -- are determined
 * at runtime in your application, rather than as you are writing and compiling
 * your application's classes.</p>
 *
 * <p>This is the set of needs that are satisfied by the static methods of the
 * {@link eu.qualityontime.commons.QPropertyUtils}
 * class, which are described further in this section.  First, however, some
 * further definitions will prove to be useful:</p>
 *
 * <p>The general set of possible property types supported by a JavaBean can be
 * broken into three categories -- some of which are supported by the standard
 * JavaBeans specification, and some of which are uniquely supported by the
 * this package:</p>
 * <ul>
 * <li><strong>Simple</strong> - Simple, or scalar, properties have a single
 *     value that may be retrieved or modified.  The underlying property type
 *     might be a Java language primitive (such as <code>int</code>, a simple
 *     object (such as a <code>java.lang.String</code>), or a more complex
 *     object whose class is defined either by the Java language, by the
 *     application, or by a class library included with the application.</li>
 * <li><strong>Indexed</strong> - An indexed property stores an ordered collection
 *     of objects (all of the same type) that can be individually accessed by an
 *     integer-valued, non-negative index (or subscript).  Alternatively, the
 *     entire set of values may be set or retrieved using an array.
 *     As an extension to the JavaBeans specification, the
 *     <em>BeanUtils</em> package considers any property whose underlying data
 *     type is <code>java.util.List</code> (or an implementation of List) to be
 *     indexed as well.</li>
 * <li><strong>Mapped</strong> - As an extension to standard JavaBeans APIs,
 *     the <em>BeanUtils</em> package considers any property whose underlying
 *     value is a <code>java.util.Map</code> to be "mapped".  You can set and
 *     retrieve individual values via a String-valued key.</li>
 * </ul>
 *
 * <p>A variety of API methods are provided in the
 * {@link eu.qualityontime.commons.QPropertyUtils} class to get and set
 * property values of all of these types.
 * In the code fragments below, assume that there are two bean classes defined
 * with the following method signatures:</p>
 * <pre>
 *     public class Employee {
 *         public Address getAddress(String type);
 *         public void setAddress(String type, Address address);
 *         public Employee getSubordinate(int index);
 *         public void setSubordinate(int index, Employee subordinate);
 *         public String getFirstName();
 *         public void setFirstName(String firstName);
 *         public String getLastName();
 *         public void setLastName(String lastName);
 *     }
 * </pre>
 *

 * <a name="standard.nested"></a>
 * <h2>2.3 Nested Property Access</h2>
 *
 * <p>In all of the examples above, we have assumed that you wished to retrieve
 * the value of a property of the bean being passed as the first argument to a
 * QPropertyUtils method.  However, what if the property value you retrieve is
 * really a Java object, and you wish to retrieve a property of <em>that</em>
 * object instead?</p>
 *
 * <p>For example, assume we really wanted the <code>city</code> property of the
 * employee's home address.  Using standard Java programming techniques for direct
 * access to the bean properties, we might write:</p>
 *
 * <pre>
 *     String city = employee.getAddress("home").getCity();
 * </pre>
 *
 * <p>The equivalent mechanism using the PropertyUtils class is called
 * <strong>nested</strong> property access.  To use this approach, you concatenate
 * together the property names of the access path, using "." separators -- very
 * similar to the way you can perform nested property access in JavaScript.</p>
 *
 * <ul>
 * <li> {@link eu.qualityontime.commons.QPropertyUtils#getProperty(Object, String)}</li>
 * <li> {@link eu.qualityontime.commons.QPropertyUtils#setProperty(Object, String, Object)}</li>
 * </ul>
 *
 * <p>The PropertyUtils equivalent to the above Java expression would be:</p>
 *
 * <pre>
 *     String city = (String)
 *       QPropertyUtils.getProperty(employee, "address(home).city");
 * </pre>
 *
 * <p>Finally, for convenience, PropertyUtils provides method signatures that
 * accept any arbitrary combination of simple, indexed, and mapped property
 * access, using any arbitrary level of nesting:</p>
 *
 * <ul>
 * <li> {@link eu.qualityontime.commons.QPropertyUtils#getProperty(Object, String)}</li>
 * <li> {@link eu.qualityontime.commons.QPropertyUtils#setProperty(Object, String, Object)}</li>
 * </ul>
 *
 * <p>which you might use like this:</p>
 *
 * <pre>
 *     Employee employee = ...;
 *     String city = (String) QPropertyUtils.getProperty(employee,
 *       "subordinate[3].address(home).city");
 * </pre>

 * <a name="FAQ"></a>
 * <h1>7. Frequently Asked Questions</h1>
 *
 * <a name="FAQ.property"></a>
 * <h2>Why Can't BeanUtils Find My Method?</h2>
 * <p>The <em>BeanUtils</em> package relies on <em>introspection</em> rather than
 * <em>reflection</em>. This means that it will find only
 * <a href='http://java.sun.com/products/javabeans'><em>JavaBean</em>
 * compliant</a> properties.</p>
 * <p>There are some subtleties  of this specification that can catch out the unwary:</p>
 * <ul>
 * <li>A property can have only one set and one get method. Overloading is not allowed.</li>
 * <li>The <code>java.beans.Introspector</code> searches widely for a custom <em>BeanInfo</em>
 * class. If your class has the same name as another with a custom <em>BeanInfo</em>
 * (typically a java API class) then the <code>Introspector</code> may use that instead of
 * creating via reflection based on your class. If this happens, the only solution is to
 * create your own <em>BeanInfo</em>.</li>
 * </ul>
 *
 */
package eu.qualityontime.commons;