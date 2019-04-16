package com.cnstock.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * 
 * @aothor cpc
 * @date 2018年8月16日
 * @param 
 * @function   
 */
public class RedisUtil {

	private static RedisTemplate<String, Object> redisTemplate;
	static int time = 604800;//七天

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 普通缓存获取
	 * @param key 键
	 * @return 值
	 */
	public static Object get(String key){
		return key==null?null:redisTemplate.opsForValue().get(key);
	}

	/**
	 * 普通缓存放入并设置时间
	 * @param key 键
	 * @param value 值
	 * @return true成功 false 失败
	 */
	public static boolean set(String key,Object value){
		try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * HashGet
	 * @param key 键 不能为null
	 * @param item 项 不能为null
	 * @return 值
	 */
	public static Object hget(String key,String item){
		return redisTemplate.opsForHash().get(key, item);
	}

	/**
	 * 获取hashKey对应的所有键值
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public static Map<Object,Object> hmget(String key){
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * HashSet
	 * @param key 键
	 * @param map 对应多个键值
	 * @return true 成功 false 失败
	 */
	public static boolean hmset(String key, Map<String,Object> map){
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取list缓存的内容
	 * @param key 键
	 * @return
	 */
	public  static List<Object> lGet(String key){
		try {
			return redisTemplate.opsForList().range(key, 0, -1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取list缓存的长度
	 * @param key 键
	 * @return
	 */
	public static long lGetListSize(String key){
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 将集合放入缓存
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static <T> boolean lSet(String key, T value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static <T> boolean  lSet(String key, List<T> value) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 通过索引 获取list中的值
	 * @param key 键
	 * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return
	 */
	public static Object lGetIndex(String key,long index){
		try {
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 删除键值
	 * @param key
	 * @return
	 */
	public static boolean delKey(String key){
		try {
			redisTemplate.delete(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}