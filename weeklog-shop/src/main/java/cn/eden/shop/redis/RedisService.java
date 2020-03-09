package cn.eden.shop.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * 因为RedisService中引用了jedisPool，但是jedisPool是一个实例化方法，
 * 也就是说创建JedisPoolFactory这个Bean时，是依赖RedisService的，RedisService中又注入了JedisPool，
 * 所以RedisService和jedisPool形成循环依赖。
 */
@Service
public class RedisService {

    /**
     * 1. 通过Jedis连接池获取连接
     * 2. 通过Jedis连接池工厂获取Jedis连接池     * @param key  获取的key
     * @param clazz
     * @param <T> 获取数据的类型
     * @return
     */
    @Autowired
    JedisCluster jedisCluster;
    @Autowired
    RedisConfig redisConfig;

    /**
     *
     * @param key
     * @param clazz  泛型类型的class类
     * @param <T> 泛型标记
     * @return
     */
    public <T> T get(String key,Class<T> clazz){
        String value=jedisCluster.get(key);
        T t=stringToBean(value,clazz);
        return t;
    }

    /**
     * Class<T> 泛型类型
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean set(String key,T obj){
        String value=beanToString(obj);
        jedisCluster.set(key,value);
        return true;
    }

    <T> T stringToBean(String str,Class<T> clazz){
        if(str ==null || str.length()<=0 || str==null){
            return null;
        }
        if(clazz ==int.class || clazz ==Integer.class){
            return (T)Integer.valueOf(str);
        }else if(clazz ==String.class){
            return (T)str;
        }else if(clazz ==long.class || clazz == Long.class){
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
        return null;
    }

    <T> String beanToString(T t){
        if(t == null){
            return null;
        }
        Class<?> clazz=t.getClass();
        if(clazz ==int.class || clazz ==Integer.class){
            return ""+t;
        }else if(clazz == String.class){
            return (String)t;
        }else if(clazz == long.class || clazz ==Long.class){
            return ""+t;
        }else{
            return JSON.toJSONString(t);
        }
    }
}
