# q-beanutils
Jakarta commons-beanutils PropertyUtils replacement.

Plan:

1. [Done] Wrap of Jakarta commons PropertyUtils to avoid checked excecptions and adding some generics friendly method.
2. Take over jakarta commons implementation and eliminate the use of DynaBeans.
3. Add field access by the `@` notation. Example: `attr1.@field1`
4. Make it working with non public beans.
5. Add null attribute access. Example: `attr1?.attr2`
6. Add collection accessors. Example. Taken `attr1` as a `List` expression `attr1.attr2` gets all the attr2 values in collection. 