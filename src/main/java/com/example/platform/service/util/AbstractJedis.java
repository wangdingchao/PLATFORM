package com.example.platform.service.util;

import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Set;

/**
 * Created by xiaoxuwang on 2019/2/25.
 */
public interface AbstractJedis {

    void set(String key, String value);

    List<String> consumer(String queue);

    void producer(String queue, String message);

    List<String> getTime();

    long setnx(String key, String value);

    long getset(String key, String value);

    String get(String key);

    void del(String key);

    void set(String key, String value, int expireTime);

    /**
     * @param nxxx NX：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value。
     *             XX ：只在键已经存在时，才对键进行设置操作。
     * @param expx EX second ：设置键的过期时间为 second 秒。
     *             PX millisecond ：设置键的过期时间为 millisecond 毫秒。
     * @return 在设置操作成功完成时，返回 OK 。
     *         如果设置了 NX 或者 XX ，但因为条件没达到而造成设置操作未执行，那么命令返回空批量回复（NULL Bulk Reply）。
     */
    default String set(String key, String value, String nxxx, String expx, int expireTime){return null;};

    String getHashField(String key, String field);

    void hash(String key, String field, String value, int expireTime);

    void hash(String key, String field, String value);

    void rList(String list, String value, int expireTime);

    void rList(String list, String value);

    void lTirm(String list, int start, int end);

    void hDel(String key, String field);

    Long getTTL(String key);

    Long incr(String key, int expireTime);

    default Long incrBy(String key, Integer increment, int expireTime){return null;};

    default Long incrBy(String key, Integer increment){return null;};

    default Long incr(String key) {return null;};

    Set<String> keys(String preStr);

    List<String> lrange(String key, long start, long end);

    void publish(String channel, String message);

    void rPush(String queue, String message);

    /**
     *
     * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。
     *  反之，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     *  注意返回值角标越界
     */
    List<String> consumer(int timeout, String queue);

    void delList(String queue);

    String rpop(String queue);

    default String lPop(String queue){return null;};

    long llen(String queue);

    default void expire(String key, int expire){
    };

    default void subscribe(JedisPubSub jedisPubSub, String channel){};

    default Set<String> smembers(String key){return null;};

    default void zAdd(String key, double score, String value, int expireTime){};

    default Boolean keyIsExists(String key){return null;};

}
