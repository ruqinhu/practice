package org.ruqinhu.reflection.factory;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.*;

public class DefaultObjectFactory implements ObjectFactory, Serializable {

    private static final long serialVersionUID = 4473498632890652417L;

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public <T> T create(Class<T> type) {
        return create(type, null, null);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        Class<?> classToCreate = resolveInterface(type);

        return (T) instantiateClass(classToCreate, constructorArgTypes, constructorArgs);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }

    private <T> T instantiateClass(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        try {
            Constructor<T> constructor;
            if (constructorArgTypes == null || constructorArgs == null) {
                constructor = type.getDeclaredConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                return constructor.newInstance();
            }
            constructor = type.getDeclaredConstructor(constructorArgTypes.toArray(new Class[constructorArgTypes.size()]));
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance(constructorArgs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //1.解析接口,将interface转为实际class
    protected Class<?> resolveInterface(Class<?> type) {
        Class<?> classToCreate;
        if (type == List.class || type == Collection.class || type == Iterable.class) {
            //List|Collection|Iterable-->ArrayList
            classToCreate = ArrayList.class;
        } else if (type == Map.class) {
            //Map->HashMap
            classToCreate = HashMap.class;
        } else if (type == SortedSet.class) { // issue #510 Collections Support
            //SortedSet->TreeSet
            classToCreate = TreeSet.class;
        } else if (type == Set.class) {
            //Set->HashSet
            classToCreate = HashSet.class;
        } else {
            //除此以外，就用原来的类型
            classToCreate = type;
        }
        return classToCreate;
    }
}
