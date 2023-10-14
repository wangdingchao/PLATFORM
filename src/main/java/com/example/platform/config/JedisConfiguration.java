package com.example.platform.config;

import com.example.platform.utils.JedisUtil;
import com.example.platform.utils.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaoxuwang on 2018/2/21.
 */
@Slf4j
public class JedisConfiguration {

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.timeout}")
    private int redisTimeout;

    @Value("${spring.redis.pool.max-active}")
    private int redisPoolMaxActive;

    @Value("${spring.redis.pool.max-idle}")
    private int redisPoolMaxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int redisPoolMinIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long redisPoolMaxWait;

    @Value("${spring.redis.sentinel.master}")
    private String masterName;

    @Value("${spring.redis.sentinel.nodes}")
    private String nodes;

    @Value("${spring.redis.pool0}")
    private int pool0;

    @Value("${spring.redis.pool1}")
    private int pool1;

    @Value("${spring.redis.pool2}")
    private int pool2;

    private JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisPoolMaxIdle);
        poolConfig.setMinIdle(redisPoolMinIdle);
        poolConfig.setMaxWaitMillis(redisPoolMaxWait);
        poolConfig.setMaxTotal(redisPoolMaxActive);
        log.info(this.toString());
        return poolConfig;
    }

    private JedisSentinelPool getJedisSentinelPool() {
        Set<String> nodeSet = new HashSet<>();
        String[] nodeArray = nodes.split(",");
        for (String node : nodeArray) {
            log.info("Read node : {}。", node);
            nodeSet.add(node);
        }
        return new JedisSentinelPool(masterName, nodeSet, getJedisPoolConfig(), redisTimeout, redisPassword, pool0);
    }

    @Bean(name = "jedisPool1")
    public JedisSentinelPool getJedisSentinelPool1() {
        Set<String> nodeSet = new HashSet<>();
        String[] nodeArray = nodes.split(",");
        for (String node : nodeArray) {
            log.info("Read node : {}。", node);
            nodeSet.add(node);
        }
        return new JedisSentinelPool(masterName, nodeSet, getJedisPoolConfig(), redisTimeout, redisPassword, pool1);
    }

    @Bean(name = "jedisPool0")
    public JedisSentinelPool getJedisSentinelPoolDefault0() {
        return  getJedisSentinelPool();
    }

    @Bean(name = "jedisPool2")
    public JedisSentinelPool getJedisSentinelPool2() {
        Set<String> nodeSet = new HashSet<>();
        String[] nodeArray = nodes.split(",");
        for (String node : nodeArray) {
            log.info("Read node : {}。", node);
            nodeSet.add(node);
        }
        return new JedisSentinelPool(masterName, nodeSet, getJedisPoolConfig(), redisTimeout, redisPassword, pool2);
    }

    @Bean
    public JedisUtil getJedisUtil() {
        return new JedisUtil(getJedisSentinelPool());
    }

    @Bean
    public RedisLockUtil getRedisLockUtil(@Autowired JedisUtil jedisUtil) {
        return new RedisLockUtil(jedisUtil);
    }

    @Override
    public String toString() {
        return "JedisConfiguration{" +
                "nodes='" + nodes + '\'' +
                ", redisPassword='" + redisPassword + '\'' +
                ", redisTimeout=" + redisTimeout +
                ", redisPoolMaxActive=" + redisPoolMaxActive +
                ", redisPoolMaxIdle=" + redisPoolMaxIdle +
                ", redisPoolMinIdle=" + redisPoolMinIdle +
                ", redisPoolMaxWait=" + redisPoolMaxWait +
                '}';
    }
}