package com.example.platform.service.util;

import com.sogal.common.domain.jedis.ScanKeyResult;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.params.SetParams;

import java.util.*;

import static com.sogal.common.util.ExceptionDescribe.MESSAGE;

/**
 * Created by xiaoxuwang on 2018/2/21.
 */
@Setter
@Getter
public class JedisUtil implements AbstractJedis {

    private static final String LOCK_SUCCESS = "OK";

    private static final Long RELEASE_SUCCESS = 1L;

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    private JedisSentinelPool jedisSentinelPool;

    public JedisUtil(JedisSentinelPool jedisSentinelPool) {
        this.jedisSentinelPool = jedisSentinelPool;
    }

    public JedisUtil() {
    }

    /**
     * 获取字符串value
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 存储字符串
     *
     * @param key
     * @param value
     * @param expireTime expireTime < 0时，不会设置失效时间
     */
    @Override
    public void set(String key, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.set(key, value);
            if (0 < expireTime) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();

            SetParams setParams = new SetParams();

            if ("xx".equalsIgnoreCase(nxxx)) {
                setParams.xx();
            } else if ("nx".equalsIgnoreCase(nxxx)) {
                setParams.nx();
            }

            if ("ex".equalsIgnoreCase(expx)) {
                setParams.ex(expireTime);
            } else if ("px".equalsIgnoreCase(expx)) {
                setParams.px(expireTime);
            }
            return jedis.set(key, value, setParams);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }

    }

    @Override
    public void set(String key, String value) {
        set(key, value, -1);
    }

    /**
     * 获取字符串value
     *
     * @param key   hash的键
     * @param field hash的域
     */
    @Override
    public String getHashField(String key, String field) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * hash类型存储
     *
     * @param field      hash域
     * @param key        键
     * @param value      值
     * @param expireTime 失效时间，小于0不设置失效时间
     */
    @Override
    public void hash(String key, String field, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.hset(key, field, value);
            if (0 < expireTime) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void hash(String key, String field, String value) {
        hash(key, field, value, -1);
    }

    /**
     * 从右存储list
     *
     * @param list       list名
     * @param value      值
     * @param expireTime 过期时间
     */
    @Override
    public void rList(String list, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.rpush(list, value);
            if (0 < expireTime) {
                jedis.expire(list, expireTime);
            }
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void rList(String list, String value) {
        rList(list, value, -1);
    }

    /**
     * 修剪list（>0正序，<0倒序）
     *
     * @param list  需要修剪的list
     * @param start 开始位置
     * @param end   解释位置
     */
    @Override
    public void lTirm(String list, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.ltrim(list, start, end);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 删除键
     *
     * @param key 需删除的键
     */
    @Override
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 删除hash里面的field
     *
     * @param key   键
     * @param field 域
     */
    @Override
    public void hDel(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.hdel(key, field);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 获取失效时间
     *
     * @param key
     * @return
     */
    @Override
    public Long getTTL(String key) {
        Jedis jedis = null;
        long ttl = 0l;
        try {
            jedis = jedisSentinelPool.getResource();
            ttl = jedis.ttl(key);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return ttl;
    }

    /**
     * 将 key 中储存的数字值增一
     *
     * @param key
     * @param expireTime
     * @return
     */
    @Override
    public Long incr(String key, int expireTime) {
        Jedis jedis = null;
        long value = 0L;
        try {
            jedis = jedisSentinelPool.getResource();
            value = jedis.incr(key);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     *  对指定key自增
     *
     * @param key key
     * @param increment 步长
     * @param expireTime 超时时间
     * @return 自增后的结果
     */
    @Override
    public Long incrBy(String key, Integer increment, int expireTime) {
        Jedis jedis = null;
        long value;
        try {
            jedis = jedisSentinelPool.getResource();
            value = jedis.incrBy(key, increment);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     *  对指定key自增
     *
     * @param key key
     * @param increment 步长
     * @return 自增后的结果
     */
    @Override
    public Long incrBy(String key, Integer increment) {
        Jedis jedis = null;
        long value;
        try {
            jedis = jedisSentinelPool.getResource();
            value = jedis.incrBy(key, increment);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 将 key 中储存的数字值增一
     */
    @Override
    public Long incr(String key) {
        Jedis jedis = null;
        long value = 0L;
        try {
            jedis = jedisSentinelPool.getResource();
            value = jedis.incr(key);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return value;
    }


    /**
     * 获取指定前缀的key
     *
     * @param preStr 指定前缀
     */
    @Override
    public Set<String> keys(String preStr) {
        Jedis jedis = null;
        Set<String> keys = new TreeSet<String>();
        try {
            jedis = jedisSentinelPool.getResource();
            keys = jedis.keys(preStr + "*");

        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return keys;
    }

    /**
     * 获取List列表
     *
     * @param key
     * @param start long，开始索引
     * @param end   long， 结束索引
     * @return List<String>
     */
    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        List<String> list = new ArrayList<String>();
        try {
            jedis = jedisSentinelPool.getResource();
            list = jedis.lrange(key, start, end);

        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return list;

    }

    private void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Override
    public void publish(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.publish(channel, message);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void producer(String queue, String message) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.lpush(queue, message);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    public void lPush(String queue, String message) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.lpush(queue, message);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void rPush(String queue, String message) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.rpush(queue, message);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public List<String> consumer(String queue) {
        return consumer(0, queue);
    }

    @Override
    public List<String> consumer(int timeout, String queue) {
        Jedis jedis = null;
        List<String> nsgs = null;
        try {
            jedis = jedisSentinelPool.getResource();
            nsgs = jedis.brpop(timeout, queue);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return nsgs;
    }

    @Override
    public void delList(String queue) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.ltrim(queue, 1, 0);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public String rpop(String queue) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.rpop(queue);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    @Override
    public String lPop(String queue) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.lpop(queue);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    @Override
    public long llen(String queue) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.llen(queue);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public void sadd(String set, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.sadd(set, value);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    public Boolean sismember(String set, String value) {
        Jedis jedis = null;
        Boolean result = false;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.sismember(set, value);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
        } finally {
            returnResource(jedis);
        }
        return result;
    }
    @Override
    public Set<String> smembers(String key) {
        Jedis jedis = null;
        Set<String> members = new TreeSet<String>();
        try {
            jedis = jedisSentinelPool.getResource();
            members = jedis.smembers(key);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return members;
    }

    public String spop(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.spop(key);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public long scard(String set) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.scard(set);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取统一时间戳
     * result.get(0)UNIX秒,result.get(1)UNIX微秒
     */
    @Override
    public List<String> getTime() {
        Jedis jedis = null;
        List<String> result = null;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.time();
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    @Override
    public long setnx(String key, String value) {
        Jedis jedis = null;
        long result = 0l;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    @Override
    public long getset(String key, String value) {
        Jedis jedis = null;
        String result = "0";
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
        } finally {
            returnResource(jedis);
        }
        return Long.parseLong(result);
    }

    @Override
    public void expire(String key, int expire) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.expire(key, expire);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String channel) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();   //取出一个连接
            jedis.subscribe(jedisPubSub, channel);    //通过subscribe 的api去订阅，入参是订阅者和频道名
        } catch (Exception e) {
            System.out.println(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void geoadd(String key, double longitude, double latitude, String member, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.geoadd(key, longitude, latitude, member);
            if (0 < expireTime) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    public Double geodist(String key, String member1, String member2, GeoUnit geoUnit) {
        Jedis jedis = null;
        Double geodist = null;
        try {
            jedis = jedisSentinelPool.getResource();
            geodist = jedis.geodist(key, member1, member2, geoUnit);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return geodist;
    }
    public Boolean setbit(String key, int offset, boolean value) {
        Jedis jedis = null;
        Boolean result = null;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.setbit(key, offset, value);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }
    public Boolean getbit(String key, int offset) {
        Jedis jedis = null;
        Boolean result = null;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.getbit(key, offset);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }
    // 获取锁, 设置超时时间，单位为毫秒
    public static boolean getLock(Jedis jedis, String lockKey, String requestId, Long expireTime) {
        /**
         * jedis.set(key, value, nxxx, expx, time)
         *
         * Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1
         * GB).
         * @param key
         * @param value
         * @param NXXX NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key if it already exist.
         * @param EXPX EX|PX, expire time units: EX = seconds; PX = milliseconds
         *
         * @return Status code reply set成功，返回 OK
         */

        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.px(expireTime);
        String result = jedis.set(lockKey, requestId, setParams);
//        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    //释放锁
    public static boolean releaseLock(Jedis jedis, String lockKey, String requestId) {
        // Lua脚本
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    @Override
    public void zAdd(String key, double score, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.zadd(key, score, value);
            if (0 < expireTime) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }
    public Set<String> zrangeByScore(String key, double min, double max){
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return jedis.zrangeByScore(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count){
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        } finally {
            returnResource(jedis);
        }
    }

    public Long zrem(String key, String... members){
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return jedis.zrem(key, members);
        } finally {
            returnResource(jedis);
        }
    }
    @Override
    public Boolean keyIsExists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }
    public ScanKeyResult scanKeyList(String prefixKey, String cursor, Integer count) {
        Jedis jedis = null;
        ScanKeyResult scanKeyResult = new ScanKeyResult();
        try {
            jedis = jedisSentinelPool.getResource();

            Set<String> resultSet = new HashSet<>();
            // 游标初始值为0
//            String cursor = ScanParams.SCAN_POINTER_START;
            ScanParams scanParams = new ScanParams();
            scanParams.match(prefixKey);
            scanParams.count(count);
            //使用scan命令获取数据，使用cursor游标记录位置，下次循环使用
            ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
            cursor = scanResult.getCursor();// 返回0 说明遍历完成
            List<String> list = scanResult.getResult();
            if(list != null) {
                resultSet.addAll(list);
            }
            scanKeyResult.setCursor(cursor);
            scanKeyResult.setKeys(resultSet);
            return scanKeyResult;
        } catch (Exception e) {
            logger.error(MESSAGE.getMessage(e));
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

}
