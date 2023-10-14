package com.example.platform.utils;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * redis分布式锁
 */
@Setter
public class RedisLockUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockUtil.class);

    private static final int defaultExpire = 10000;

    private JedisUtil jedisUtil;

    public RedisLockUtil(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }

    /*基于redis的分布式锁
     * 适用用于单节点或者哨兵模式的redis部署
     * lock实现：
     *      1、setnx(lockkey, 1) 如果返回 0，则说明占位失败；如果返回 1，则说明占位成功
     *      2、expire() 命令对 lockkey 设置超时时间，为的是避免死锁问题。
     *      3、执行完业务代码后，可以通过 delete 命令删除 key。
     * progressLock实现：（CAS原理）
     *      1、setnx(lockkey, 当前时间+过期超时时间)，如果返回 1，则获取锁成功；如果返回 0 则没有获取到锁，转向 2
     *      2、get(lockkey) 获取值 oldExpireTime ，并将这个 value 值与当前的系统时间进行比较，
     *         如果小于当前系统时间，则认为这个锁已经超时，可以允许别的请求重新获取，转向 3
     *      3、计算 newExpireTime = 当前时间+过期超时时间，然后 getset(lockkey, newExpireTime) 会返回当前 lockkey 的值currentExpireTime
     *      4、判断 currentExpireTime 与 oldExpireTime 是否相等，如果相等，说明当前 getset 设置成功，获取到了锁。
     *         如果不相等，说明这个锁又被别的请求获取走了，那么当前请求可以直接返回失败，或者继续重试
     *      5、在获取到锁之后，当前线程可以开始自己的业务处理，当处理完毕后，比较自己的处理时间和对于锁设置的超时时间，
     *         如果小于锁设置的超时时间，则直接执行 delete 释放锁；如果大于锁设置的超时时间，则不需要再锁进行处理
     */

    /**
     * 加锁
     * @param key
     *      加锁对象
     * @param expire
     *      锁过期时间(单位毫秒)
     * @return
     *      true:成功----false:失败
     */
    public boolean lock(String key, int expire) {
        /*long status = jedisUtil.setnx(key, "1");
        if (status == 1) {
            jedisUtil.expire(key, expire);
            return true;
        }
        return false;*/
        String result = jedisUtil.set(key, "1", NX, PX, expire);
        return SUCCESS.equals(result);
    }

    public static final String NX = "NX";
    public static final String PX = "PX";
    public static final String SUCCESS = "OK";

    public boolean lock(String key) {
        return lock(key, defaultExpire);
    }

    /**
     * 竞争获取锁
     * @param key
     *      加锁对象
     * @param expire
     *      锁过期时间(单位毫秒)
     */
    public void getLock(String key, int expire) {
        boolean flag = true;
        logger.info("key = " + key + "开始获取锁");
        while (flag) {
            boolean lock = lock(key, expire);
            if (lock) {
                flag = false;
                logger.info("key = " + key + "获取锁成功");
            }
        }
    }

    public void getLock(String key) {
        getLock(key, defaultExpire);
    }

    /**
     * 加锁
     * @param key
     *      加锁对象
     * @param expire
     *      锁过期时间(单位毫秒)
     * @return
     *      true:成功----false:失败
     */
    public boolean progressLock(String key, int expire) {
        long value = System.currentTimeMillis();
        long status = jedisUtil.setnx(key, String.valueOf(value + expire));
        if (status == 1) {
            return true;
        }
        String s = jedisUtil.get(key);
        if (null == s) {
            return false;
        }
        long oldExpire = Long.parseLong(s);
        if (oldExpire < value) {
            long nowExpire = System.currentTimeMillis() + expire;
            long currentExpire = jedisUtil.getset(key, String.valueOf(nowExpire));
            if (currentExpire == oldExpire) {
                return true;
            }
        }
        return false;
    }


    public boolean progressLock(String key) {
        return progressLock(key, defaultExpire);
    }

    /**
     * 竞争获取锁
     * @param key
     *      加锁对象
     * @param expire
     *      锁过期时间(单位毫秒)
     */
    public void getProgressLock(String key, int expire) {
        boolean flag = true;
        logger.info("key = " + key + "开始获取优化锁");
        while (flag) {
            boolean lock = progressLock(key, expire);
            if (lock) {
                flag = false;
                logger.info("key = " + key + "获取优化锁成功");
            }
        }
    }

    public void getProgressLock(String key) {
        getProgressLock(key, defaultExpire);
    }

    /**
     * 释放锁
     * @param key
     *      需要释放的对象
     */
    public void unLock(String key) {
        jedisUtil.del(key);
        // logger.info("释放key = " + key + "的锁");
    }

    /**
     * 释放锁
     * @param key
     *      需要释放的对象
     */
    public void unProgressLock(String key) {
        long oldExpire = Long.parseLong(jedisUtil.get(key));
        if (oldExpire > System.currentTimeMillis()) {
            jedisUtil.del(key);
            // logger.info("释放key = " + key + "的优化锁");
        }
    }
}
