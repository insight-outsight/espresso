
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
 
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
 
import lombok.extern.slf4j.Slf4j;
 
/**
 * <p>
 * 缓存配置
 * </p>
 */
@Configuration
@EnableCaching
@Slf4j
public class SpringBootCacheConfig {
 
    @Bean("redisCacheManager")
    @Primary
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
      RedisSerializer<String> redisSerializer=new StringRedisSerializer();
      Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer=getCustomCommonJackson2JsonRedisSerializer();
      // 设置一个初始化的缓存空间set集合
      Set<String> cacheNames =  new HashSet<>();
      cacheNames.add("MULTI_LANGUAGE_CACHE");
      cacheNames.add("WEB_PAGE_CACHE");
      cacheNames.add("WEB_PAGE_AWARD");
 
      // 对每个缓存空间应用不同的配置
      Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
 
      configMap.put("MULTI_LANGUAGE_CACHE", newRedisCacheConfiguration(redisSerializer,jackson2JsonRedisSerializer,60));
      configMap.put("WEB_PAGE_CACHE", newRedisCacheConfiguration(redisSerializer,jackson2JsonRedisSerializer,120));
      configMap.put("WEB_PAGE_AWARD", newRedisCacheConfiguration(redisSerializer,jackson2JsonRedisSerializer,180));
 
 
          .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
          .withInitialCacheConfigurations(configMap)
          .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
          .build();
      return cacheManager;
    }
 
    @Bean("redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
      RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
      redisTemplate.setConnectionFactory(connectionFactory);
      //set redis key serializer with string
      StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
      redisTemplate.setKeySerializer(stringRedisSerializer);
      redisTemplate.setHashKeySerializer(stringRedisSerializer);
 
      // set redis value serializer with fastJson
      Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer=getCustomCommonJackson2JsonRedisSerializer();
      redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
      redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
      redisTemplate.afterPropertiesSet();
      return redisTemplate;
    }
 
 
    @Bean("stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
      return new StringRedisTemplate(connectionFactory);
    }
 
    private Jackson2JsonRedisSerializer<Object> getCustomCommonJackson2JsonRedisSerializer() {
 
      Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
      ObjectMapper objectMapper = new ObjectMapper();
      //指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
      objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
      // 指定序列化输入的类型，类必须是非final修饰的
      objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
      jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
      return jackson2JsonRedisSerializer;
    }
 
    private  RedisCacheConfiguration newRedisCacheConfiguration(RedisSerializer<String> keySerializer,
        RedisSerializer<?> valueSerializer ,long expiredSeconds){
      RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
      return config.entryTtl(Duration.ofSeconds(expiredSeconds))
                  /*.disableCachingNullValues();   */  // 不缓存空值
          .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
          .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer));
    }
    
    public enum CacheConfigurationEnum {
 
        USER_LEVEL_CACHE(3600, 1000, 100000), 
        USER_WEB_CHARGE_RECORD(14400, 1000, 100000),
        USER_COIN_RECORD_CACHE(28800, 1000, 100000);
 
        private int maxSize;
        private int initialSize;
        private int ttl;
 
        private CacheConfigurationEnum(int ttl, int initialSize, int maxSize) {
            this.ttl = ttl;
            this.initialSize = initialSize;
            this.maxSize = maxSize;
        }
 
        public int getMaxSize() {
            return maxSize;
        }
 
        public int getInitialSize() {
            return initialSize;
        }
 
        public int getTtl() {
            return ttl;
        }
 
    }
 
    @Bean("caffeineCacheManager")
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches = new ArrayList<>();
        for (CacheConfigurationEnum c : CacheConfigurationEnum.values()) {
            Cache<Object, Object> caffeineCache = Caffeine.newBuilder()
                    .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                    .maximumSize(c.getMaxSize())
                    .recordStats()
                    .build();
            CaffeineCache cache = new CaffeineCache(c.name(), caffeineCache);
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(()  -> 
                    log.info("Caffine Cache name:{}, size:{}, hitRate:{}, hitCount:{}, missCount:{}, evictionCount:{}", 
                            c.name(), caffeineCache.estimatedSize(), caffeineCache.stats().hitRate(),
                            caffeineCache.stats().hitCount(),  caffeineCache.stats().missCount(),
                            caffeineCache.stats().evictionCount()),
                    10, 10, TimeUnit.SECONDS);
            caches.add(cache);
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }
 
//  @Bean("caffeineCacheManager")
//  public CaffeineCacheManager caffeineCacheManager() {
//      CaffeineCacheManager cacheManager = new CaffeineCacheManager();
//      cacheManager.setCaffeine(Caffeine.newBuilder()
//              .initialCapacity(10000)
//              .maximumSize(100000)
//              .expireAfterWrite(5, TimeUnit.SECONDS)
//              .recordStats());
//      return cacheManager;
//  }
 
}
