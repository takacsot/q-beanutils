# q-beanutils
Jakarta commons-beanutils PropertyUtils replacement.

Install:

	<dependency>
			<groupId>eu.qualityontime.commons</groupId>
			<artifactId>q-beanutils</artifactId>
			<version>0.8</version>
	</dependency>

Plan:

1. [Done] Wrap of Jakarta commons PropertyUtils to avoid checked excecptions and adding some generics friendly method.
2. [Done] Take over jakarta commons implementation and eliminate the use of DynaBeans.
3. [Prog]Add field access by the `@` notation. Example: `attr1.@field1`
  1. [Done]set and get field
  2. copy properties map to field
  3. copy properties obj from field/attr to field/attr
4. Make it working with non public beans.
5. [Done] Add null attribute access. Example: `attr1?.attr2`
6. Add collection accessors. Example. Taken `attr1` as a `List` expression `attr1*.attr2` gets all the attr2 values in collection. See Groovy spread operator. (http://docs.groovy-lang.org/latest/html/documentation/#_spread_operator)

Nullable attribute access has a limitation. 
When the attribute is accesse like `getIndexedProp(int index)` then the underlying method must take care of nullability.

As `QPropertyUtils` is behaving like commona PropertyUtils most behaviours are the same (http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.3/apidocs/org/apache/commons/beanutils/package-summary.html)

