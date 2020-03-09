package cn.eden.redis;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
/**
 * spring-data-2.0以上版本配置
 */
@Component
public class JedisClusterUtils {

    @Bean
    @ConfigurationProperties(prefix="redis.cluster")
    public RedisClusterConfiguration redisClusterConfiguration(){
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        ArrayList<RedisNode> list = new ArrayList<>();
        list.add(new RedisNode("www.weeklog.com",7000));
        list.add(new RedisNode("www.weeklog.com",7001));
        list.add(new RedisNode("www.weeklog.com",7002));
        list.add(new RedisNode("www.weeklog.com",7003));
        list.add(new RedisNode("www.weeklog.com",7004));
        list.add(new RedisNode("www.weeklog.com",7005));
        redisClusterConfiguration.setClusterNodes(list);
        return  redisClusterConfiguration;
    }

    /**
     * 连接池配置
     * @return
     */
    @Bean
    /**
     * 表示从配置文件读取以前缀开头的信息，注入到GenericObjectPoolConfig中
     */
    @ConfigurationProperties(prefix="generic.pool.config")
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        return poolConfig;
    }

    /**
     * @Primary 解决重复实现类
     * 加入连接池配置信息
     * lettuce客户端配置信息（如果不用连接池通过LettuceClientConfiguration来builder）
     */
    @Bean(name = "lettuceClientConfiguration")
    public LettuceClientConfiguration lettuceClientConfiguration(GenericObjectPoolConfig genericObjectPoolConfig){
        LettucePoolingClientConfiguration build = LettucePoolingClientConfiguration
                                                  .builder().poolConfig(genericObjectPoolConfig)
                                                  .build();
        return build;
    }

    /**
     *
     * @param redisClusterConfiguration    redis集群配置
     * @param lettuceClientConfiguration   Lettuce客户端配置
     * @return
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(RedisClusterConfiguration redisClusterConfiguration, LettuceClientConfiguration lettuceClientConfiguration){
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisClusterConfiguration,lettuceClientConfiguration);
        return lettuceConnectionFactory;
    }


    @Bean
    public RedisTemplate<String, Object> redisCacheTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new
                Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }
}
