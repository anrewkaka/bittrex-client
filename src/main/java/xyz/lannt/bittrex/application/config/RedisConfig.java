package xyz.lannt.bittrex.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

  @Bean
  private StringRedisTemplate redisTemplate() {
    return new StringRedisTemplate();
  };
}
