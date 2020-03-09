package cn.eden.repository.mybatisRepo.base;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 内省机制：操作JavaBean的API,用来访问某个属性的getter和setter方法
 * 核心类是Introspector,它可以拿到JavaBean的所有信息
 * PropertyDescriptors:可根据字段获取该字段的get/set方法
 * MethodDescriptors: 可获取方法的元信息
 */
public class MBUtils {
    /**
     * 利用Introspector,PropertyDescriptor实现 MAP———>Bean
     * @param map
     * @param cla
     */
    public static Object transMap2Bean(Map<String, Object> map, Class cla) {
        Object obj = null;
        try {
            obj = cla.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (map == null || obj == null) {
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(cla);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor prop : propertyDescriptors) {
                String key = prop.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    Method setter = prop.getWriteMethod();
                    setter.invoke(obj,value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 利用Introspector,PropertyDescriptor实现 Bean———>Map
     * @param map
     * @param cla
     */
    public static Map<String,Object> transBean2Map(Map<String,Object> map,Class cla){
        Object obj = null;
        try {
            obj = cla.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (map == null || obj == null) {
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(cla);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor prop: propertyDescriptors) {
                String key=prop.getName();
                if(!"class".equals(key)){//过滤class属性
                    Method getter = prop.getReadMethod();
                    map.put(key,getter.invoke(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
