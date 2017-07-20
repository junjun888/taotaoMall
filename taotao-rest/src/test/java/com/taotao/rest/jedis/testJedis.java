package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class testJedis {
	
	@Test
	public void testJedisSingle() {
		// 创建一个jedis对象
		Jedis jedis = new Jedis("192.168.253.128", 6379);
		// 调用 jedis对象的方法，方法名称和redis 一致
		jedis.set("11", "testJedis");
		String str = jedis.get("11");
		System.out.println(str);
		// 关闭 jedis
		jedis.close();
	}
	
	/**
	 * 使用jedis连接池
	 */
	@Test
	public void testJedisPool() {
		// 创建 jedis 连接池
		JedisPool jedisPool = new JedisPool("192.168.253.128", 6379);
		// 从连接池中获取 jedis对象
		Jedis jedis = jedisPool.getResource();
		jedis.set("jedisPool", "test jedis pool 111");
		String str = jedis.get("jedisPool");
		System.out.println(str);
		// 关闭 jedis 和 pool
		jedis.close();
		jedisPool.close();
	}
	
	/**
	 * redis集群测试
	 */
	@Test
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.253.128", 7001));
		nodes.add(new HostAndPort("192.168.253.128", 7002));
		nodes.add(new HostAndPort("192.168.253.128", 7003));
		nodes.add(new HostAndPort("192.168.253.128", 7004));
		nodes.add(new HostAndPort("192.168.253.128", 7005));
		nodes.add(new HostAndPort("192.168.253.128", 7006));
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("jedisCluster", "this is a jedisCluster add value");
		String str = jedisCluster.get("jedisCluster");
		System.out.println(str);
		jedisCluster.close();
	}
	
	/**
	 * Test Sping jedisCluster
	 * 
	 */
	@Test
	public void testSpringJedisCluster() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/application-*.xml");
		System.out.println(applicationContext == null);
		JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
		jedisCluster.set("jedisCluster", "this is a jedisCluster add value");
		String str = jedisCluster.get("jedisCluster");
		System.out.println(str);
		jedisCluster.close();
	}
}