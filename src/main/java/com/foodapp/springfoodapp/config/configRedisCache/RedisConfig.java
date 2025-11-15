//package com.foodapp.springfoodapp.config.configRedisCache;
//
//import com.foodapp.springfoodapp.entiry.*;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//
//import java.time.Duration;
//
//@Configurable
//public class RedisConfig {
//
////    public static final String PRODUCT_CACHE = "products";
////    @CachePut(value = PRODUCT_CACHE, key = "#result.id()")
////    @Cacheable(value = PRODUCT_CACHE, key = "#productId")
////    @CachePut(value = PRODUCT_CACHE, key = "#result.id()")
////    @CacheEvict(value = PRODUCT_CACHE, key = "#productId")
//
//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10))
//                .disableCachingNullValues()
//
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                        new Jackson2JsonRedisSerializer<>(Address.class))
//                )
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                        new Jackson2JsonRedisSerializer<>(Bill.class)
//                ))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                        new Jackson2JsonRedisSerializer<>(Category.class)
//                ))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                        new Jackson2JsonRedisSerializer<>(Customer.class)
//                ))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                        new Jackson2JsonRedisSerializer<>(FoodCart.class)
//                ))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                        new Jackson2JsonRedisSerializer<>(Item.class)
//                ))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                      new Jackson2JsonRedisSerializer<>(Restaurant.class)
//                ))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                        new Jackson2JsonRedisSerializer<>(OrderDetails.class)
//                ));
//
//        return RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(cacheConfiguration)
//                .build();
//    }
//}
