//package cn.eden.shop.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPoolConfig;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class JedisClusterUtils {
//
//    @Autowired
//    RedisConfig redisConfig;
//
//    JedisPoolConfig getJedisPoolConfig(){
//        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
//        jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
//        jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
//        return jedisPoolConfig;
//    }
//
//    @Bean
//    JedisCluster getJedisCluster(){
//        //1. 配置Jedsi连接池
//        JedisPoolConfig jedisPoolConfig=getJedisPoolConfig();
//        //2. 创建集群的节点集合
//        Set<HostAndPort> nodes = new HashSet<>();
//        String hostPorts=redisConfig.getHost();
//        String[] hosts=hostPorts.split(",");
//        for (String str: hosts) {
//            nodes.add(new HostAndPort(str.split(":")[0], Integer.valueOf(str.split(":")[1])));
//        }
//        //3. 创建Jedis连接池
////        JedisCluster jedisCluster=new JedisCluster(nodes, redisConfig.getTimeout(), redisConfig.getTimeout(),
////                2, redisConfig.getPassword(),jedisPoolConfig);
//        JedisCluster jedisCluster=new JedisCluster(nodes,jedisPoolConfig);
//        return jedisCluster;
//    }
//}
