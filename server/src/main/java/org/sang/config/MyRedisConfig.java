package org.sang.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sang.bean.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class MyRedisConfig {

//    @Bean
////    public RedisTemplate<Object, Article> arcRedisTemplate(
////            RedisConnectionFactory redisConnectionFactory)
////            throws UnknownHostException {
////        RedisTemplate<Object, Article> template = new RedisTemplate<Object, Article>();
////        template.setConnectionFactory(redisConnectionFactory);
////        Jackson2JsonRedisSerializer<Article> ser = new Jackson2JsonRedisSerializer<Article>(Article.class);
////        template.setDefaultSerializer(ser);
////        return template;
////    }
////
////
////
////    //CacheManagerCustomizers可以来定制缓存的一些规则
////    @Primary
////    @Bean
////    public RedisCacheManager articleCacheManager(RedisTemplate<Object, Article> arcRedisTemplate){
////        RedisCacheManager cacheManager = new RedisCacheManager(arcRedisTemplate);
////        //key多了一个前缀
////
////        //使用前缀，默认会将CacheName作为key的前缀
////        cacheManager.setUsePrefix(true);
////
////        return cacheManager;
////    }

//    @Primary //将某个缓存管理器作为默认的
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//
//        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
//        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(mapper);
//
//        template.setValueSerializer(serializer);
//        //使用StringRedisSerializer来序列化和反序列化redis的key值
//        template.setKeySerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }

}
