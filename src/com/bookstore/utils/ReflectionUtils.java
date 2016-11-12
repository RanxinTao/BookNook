package com.bookstore.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {
	
	public static Class getSuperClassGenericType(Class clazz, int index) {
		Type superClass = clazz.getGenericSuperclass();
		if(!(superClass instanceof ParameterizedType))
			return Object.class;
		
		Type[] params = ((ParameterizedType)superClass).getActualTypeArguments();		
		if(index >= params.length || index < 0 || !(params[index] instanceof Class))
			return Object.class;
		
		return (Class) params[index];	
	}
	
	public static Class getSuperClassGenericType(Class clazz) {
		return getSuperClassGenericType(clazz, 0);
	}
	
	public static Method getDeclaredMethod(Object object, String methodName, Class[] parameterTypes) {
		for(Class clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				return clazz.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {}
		}
		return null;
	}
	
	public static void makeAccessible(Field field) {
		if(!Modifier.isPublic(field.getModifiers()))
			field.setAccessible(true);
	}
	
	public static Field getDeclaredField(Object object, String fieldName) {
		for(Class clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {}
		}
		return null;
	}
	
	public static Object invokeMethod(Object object, String methodName, Class[] parameterTypes, 
			Object[] parameters) throws InvocationTargetException {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if(method == null)
			throw new IllegalArgumentException("Could not find method [" + methodName + "] in target [" + object + "]");
		method.setAccessible(true);
		
		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException e) {}
		return null;
	}
	
	public static void setFieldValue(Object object, String fieldName, Object value) {
		Field field = getDeclaredField(object, fieldName);
		if(field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] in target [" + object + "]");
		makeAccessible(field);
		
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {}
	}
	
	public static Object getFieldValue(Object object, String fieldName) {
		Field field = getDeclaredField(object, fieldName);
		if(field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] in target [" + object + "]");
		makeAccessible(field);
		
		Object res = null;
		try {
			res = field.get(object);
		} catch (IllegalAccessException e) {}
		return res;
	}
	
}
