package com.acuity.inject.replacements;

import com.acuity.api.annotations.ClientInvoked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Zachary Herridge on 7/6/2017.
 */
public class RReflection {

    private static Logger logger = LoggerFactory.getLogger(RReflection.class);

    @ClientInvoked
    public static Class<?> findClass(String className) throws ClassNotFoundException {
        logger.debug("RS-Reflection finding class '{}'.", className);
        return Class.forName(className);//http://i.imgur.com/sO0wCM1.png
    }

    @ClientInvoked
    public static String getMethodName(Method method){
        logger.debug("RS-Reflection getting name of method {}.", method);
        return method.getName();
    }

    @ClientInvoked
    public static Class[] getParameterTypes(Method method){
        logger.debug("RS-Reflection finding method parameters {}.", method);
        return method.getParameterTypes();
    }

    @ClientInvoked
    public static Field findField(Class clazz, String fieldName) throws NoSuchFieldException {
        logger.debug("RS-Reflection finding field '{}' in {}.", fieldName, clazz);
        return clazz.getDeclaredField(fieldName);
    }

    @ClientInvoked
    public static Object invoke(Method method, Object instance, Object[] params) throws InvocationTargetException, IllegalAccessException {
        logger.debug("RS-Reflection invoking method {} on instance {}, with params {}.", method, instance, Arrays.toString(params));
        return method.invoke(instance, params);
    }


    @ClientInvoked
    public static void setInt(Field field, Object instance, int value) throws IllegalAccessException {
        logger.debug("RS-Reflection setting field {} instance {} to {}.", field, instance, value);
        field.setInt(instance, value);
    }

    @ClientInvoked
    public static int getModifiers(Field field) {
        logger.debug("RS-Reflection getting modifiers of field {}.", field);
        return field.getModifiers();
    }

    @ClientInvoked
    public static Class getReturnType(Method method) {
        logger.debug("RS-Reflection getting return-type of method {}.", method);
        return method.getReturnType();
    }


    @ClientInvoked
    public static int getModifiers(Method method) {
        logger.debug("RS-Reflection getting modifiers of method {}.", method);
        return method.getModifiers();
    }

    @ClientInvoked
    public static int getInt(Field field, Object instance) throws IllegalAccessException {
        logger.debug("RS-Reflection getting field {} int value instance {}.", field, instance);
        return field.getInt(instance);
    }
}
