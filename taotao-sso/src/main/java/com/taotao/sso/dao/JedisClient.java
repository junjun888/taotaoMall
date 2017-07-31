package com.taotao.sso.dao;

public interface JedisClient {
	
	/**
	 * 获取缓存数据库中的值
	 * <p>Title: get</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 */
	String get(String key);
	
	/**
	 * 在缓存数据库中设置值
	 * <p>Title: set</p>
	 * <p>Description: </p>
	 * @param key
	 * @param value
	 * @return
	 */
	String set(String key, String value);
	
	/**
	 * 获取缓存数据库中的hash 值
	 * <p>Title: hget</p>
	 * <p>Description: </p>
	 * @param hkey
	 * @param key
	 * @return
	 */
	String hget(String hkey, String key);
	
	/**
	 * 设置缓存数据库中的哈希值
	 * <p>Title: hset</p>
	 * <p>Description: </p>
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	long hset(String hkey, String key, String value);
	
	/**
	 * 获取一个自增长的值
	 * <p>Title: incr</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 */
	long incr(String key);
	
	/**
	 * 为指定的值设置一个期限
	 * <p>Title: expire</p>
	 * <p>Description: </p>
	 * @param key
	 * @param second
	 * @return
	 */
	long expire(String key, int second);
	
	/**
	 * 查看指定值的期限，如果过期 则返回 -2
	 * <p>Title: ttl</p>
	 * <p>Description: </p>
	 * @param key
	 * @return
	 */
	long ttl(String key);
	
	/**
	 * 删除指定key值得缓存
	 */
	long del(String key);
	
	/**
	 * 删除指定hkey 和 key 值得缓存
	 */
	long hdel(String hkey, String key);
}