# q-beanutils
Jakarta commons-beanutils PropertyUtils replacement.

Plan:

1. [Done] Wrap of Jakarta commons PropertyUtils to avoid checked excecptions and adding some generics friendly method.
2. [Done] Take over jakarta commons implementation and eliminate the use of DynaBeans.
3. [Prog]Add field access by the `@` notation. Example: `attr1.@field1`
3.1 [Done]set and get field
3.2 copy properties map to field
3.3 copy properties obj from field/attr to field/attr
4. Make it working with non public beans.
5. [Done] Add null attribute access. Example: `attr1?.attr2`
6. Add collection accessors. Example. Taken `attr1` as a `List` expression `attr1*.attr2` gets all the attr2 values in collection. See Groovy spread operator. (http://docs.groovy-lang.org/latest/html/documentation/#_spread_operator)

Nullable attribute access has a limitation. 
When the attribute is accesse like `getIndexedProp(int index)` then the underlying method must take care of nullability.