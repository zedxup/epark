package com.udsp.sdc.icbc.Controller.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Sentinel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * redis配置
 * 
 * @author 李炀
 * @date 2019-08-21
 *
 *
 */
@Configuration
public class RedisConfig {

	/**
	 * 创建jedis pool配置
	 * 
	 * @param props
	 *            pool参数
	 * @return jedis配置对象
	 */
	public JedisPoolConfig getJedisPoolConfig(Pool props) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(props.getMaxActive());
		config.setMaxIdle(props.getMaxIdle());
		config.setMinIdle(props.getMinIdle());
		config.setMaxWaitMillis(props.getMaxWait());
		return config;
	}

	/**
	 * 创建redis哨兵配置
	 * 
	 * @param sentinel
	 *            哨兵参数
	 * @return redis哨兵配置对象
	 */
	public RedisSentinelConfiguration getRedisSentinelConfiguration(Sentinel sentinel) {
		RedisSentinelConfiguration cfg = new RedisSentinelConfiguration();
		cfg.setMaster(sentinel.getMaster());
		String[] nodeArray = sentinel.getNodes().split(",");
		for (String node : nodeArray) {
			String[] parts = StringUtils.split(node, ":");
			cfg.addSentinel(new RedisNode(parts[0], Integer.parseInt(parts[1])));
		}
		return cfg;
	}

	/**
	 * 创建rdis配置
	 * 
	 * @return redis参数对象
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	@Primary
	public RedisProperties getRedisConnectionConfiguration() {
		return new RedisProperties();
	}

	/**
	 * 创建jedis链接工厂类
	 * 
	 * @param properties
	 *            jedis连接参数
	 * @return jedis连接工厂对象
	 */
	@Bean
	@Primary
	public JedisConnectionFactory getJedisConnectionFactory(RedisProperties properties) {
		Sentinel sentinel = properties.getSentinel();
		if (sentinel == null) {
			JedisConnectionFactory factory = new JedisConnectionFactory(getJedisPoolConfig(properties.getPool()));
			factory.setPassword(properties.getPassword());
			factory.setDatabase(properties.getDatabase());
			factory.setHostName(properties.getHost());
			factory.setPort(properties.getPort());
			return factory;
		} else {
			RedisSentinelConfiguration scfg = getRedisSentinelConfiguration(properties.getSentinel());
			JedisConnectionFactory factory = new JedisConnectionFactory(scfg, getJedisPoolConfig(properties.getPool()));
			factory.setPassword(properties.getPassword());
			factory.setDatabase(properties.getDatabase());
			return factory;
		}
	}

	/**
	 * 创建redistemplate
	 * 
	 * @param redisConnectionFactory
	 *            redis连接工厂
	 * @return redis模板类
	 */
	@Bean
	@Primary
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		return template;
	}

	/**
	 * 创建stringredistemplate
	 * 
	 * @param redisConnectionFactory
	 *            redis连接工厂
	 * @return redis模板类
	 */
	@Bean
	@Primary
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	/**
	 * 缓存用redis配置
	 * 
	 * @return
	 */
	@Bean("sso.redis.prop")
	@ConfigurationProperties(prefix = "sso.redis")
	public RedisProperties getPropertisForSSOCache() {
		return new RedisProperties();
	}

	/**
	 * 创建jedis链接工厂类
	 * 
	 * @param properties
	 *            jedis连接参数
	 * @return jedis连接工厂对象
	 */
	@Bean("sso.redis.factory")
	public JedisConnectionFactory getJedisConnectionFactoryForSSOCache(
			@Qualifier("sso.redis.prop") RedisProperties properties) {
		Sentinel sentinel = properties.getSentinel();
		if (sentinel == null) {
			JedisConnectionFactory factory = new JedisConnectionFactory(getJedisPoolConfig(properties.getPool()));
			factory.setPassword(properties.getPassword());
			factory.setDatabase(properties.getDatabase());
			factory.setHostName(properties.getHost());
			factory.setPort(properties.getPort());
			return factory;
		} else {
			RedisSentinelConfiguration scfg = getRedisSentinelConfiguration(properties.getSentinel());
			JedisConnectionFactory factory = new JedisConnectionFactory(scfg, getJedisPoolConfig(properties.getPool()));
			factory.setPassword(properties.getPassword());
			factory.setDatabase(properties.getDatabase());
			return factory;
		}
	}

	/**
	 * 创建redistemplate
	 * 
	 * @param redisConnectionFactory
	 *            redis连接工厂
	 * @return redis模板类
	 */
	@Bean("sso.redis.template")
	public RedisTemplate<String, Object> redisTemplateForSSOCache(
			@Qualifier("sso.redis.factory") RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}

	@Bean("sso.redis.string.template")
	public StringRedisTemplate stringRedisTemplateForSSOCache(
			@Qualifier("sso.redis.factory") RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
