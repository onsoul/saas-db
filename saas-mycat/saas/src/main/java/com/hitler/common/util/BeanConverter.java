package com.hitler.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.persistence.Transient;

/** 
*转换器 
*1:将JavaBean 转换成Map、JSONObject 
*2:将JSONObject 转换成Map 
* 
* @author onsoul by JT 2015-6-24 下午4:32:23
*/ 
public class BeanConverter 
{ 
    /** 
     * 将javaBean转换成Map 
     * 
     * @param javaBean javaBean 
     * @return Map对象 
     */ 
    public static Map<String, String> toMap(Object javaBean) 
    {
        Map<String, String> result = new HashMap<String, String>(); 
        Method[] methods = javaBean.getClass().getDeclaredMethods(); 
        
        for (Method method : methods) 
        { 
            try 
            { 
                if (method.getName().startsWith("get")) 
                { 
                    String field = method.getName(); 
                    field = field.substring(field.indexOf("get") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 

                    Object value = method.invoke(javaBean, (Object[])null); 
                    result.put(field, null == value ? "" : value.toString()); 
                } 
            } 
            catch (Exception e) 
            { 
            } 
        } 

        return result; 
    } 
    
    /** 
     * 将javaBean转换成Map 
     * 过滤掉@Transient的属性
     * @param javaBean javaBean 
     * @return Map对象 
     */ 
    public static Map<String, String> toMapFilter(Object javaBean) 
    {
        Map<String, String> result = new HashMap<String, String>(); 
        Method[] methods = javaBean.getClass().getDeclaredMethods(); 
        
        for (Method method : methods) 
        { 
            try 
            { 
                if (method.getName().startsWith("get")) 
                { 
                    String field = method.getName(); 
                    field = field.substring(field.indexOf("get") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 
                    
                    //过滤掉属性中带有注解的字段
                    Field property=javaBean.getClass().getDeclaredField(field);
                    System.out.println(property);
                    if (property.isAnnotationPresent(Transient.class)) {
                    	continue;
                    }
                    
                    Object value = method.invoke(javaBean, (Object[])null); 
                    result.put(field, null == value ? "" : value.toString()); 
                } 
            } 
            catch (Exception e) 
            { 
            } 
        } 

        return result; 
    } 

    /** 
     * 将map转换成Javabean 
     * 
     * @param javabean javaBean 
     * @param data map数据 
     */ 
    public static Object toJavaBean(Object javabean, Map<String, String> data) 
    { 
        Method[] methods = javabean.getClass().getDeclaredMethods(); 
        for (Method method : methods) 
        { 
            try 
            { 
                if (method.getName().startsWith("set")) 
                { 
                    String field = method.getName(); 
                    field = field.substring(field.indexOf("set") + 3); 
                    field = field.toLowerCase().charAt(0) + field.substring(1); 
                    method.invoke(javabean, new Object[] 
                    { 
                        data.get(field) 
                    }); 
                } 
            } 
            catch (Exception e) 
            { 
            } 
        } 

        return javabean; 
    } 
    
}