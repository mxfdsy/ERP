package com.lmfun.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

@EnableCaching
@Configuration  
public class RedisCacheConfig extends CachingConfigurerSupport {
    @Value("${redis.expire.seconds}")
    private Integer expireSeconds;
    
	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};

	}
	
	
	  /** 
     * redis 数据库连接池 
     * @return 
     */  
  
//    @Bean  
//    public JedisConnectionFactory redisConnectionFactory(Environment env) {  
//        JedisConnectionFactory factory = new JedisConnectionFactory();  
//        factory.setHostName(env.getProperty("spring.redis.host"));  
//        factory.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));  
//        factory.setTimeout(36000); // 设置连接超时时间  
//        System.err.println("________________________________设置连接超时时间 ");
//        return factory;  
//    }
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
	    
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}
	
	
	@Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(expireSeconds); // Sets the default expire time
		return cacheManager;
	}
}
