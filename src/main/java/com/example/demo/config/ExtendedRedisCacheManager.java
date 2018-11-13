package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;
import org.apache.commons.lang3.math.NumberUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Pattern;

public class ExtendedRedisCacheManager extends RedisCacheManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ExtendedRedisCacheManager(RedisOperations redisOperations) {
        super(redisOperations);
    }

    public ExtendedRedisCacheManager(RedisOperations redisOperations, Collection<String> cacheNames) {
        super(redisOperations, cacheNames);
    }

    public ExtendedRedisCacheManager(RedisOperations redisOperations, Collection<String> cacheNames, boolean cacheNullValues) {
        super(redisOperations, cacheNames, cacheNullValues);
    }

    private static final ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");

    private static final Pattern pattern = Pattern.compile("[+\\-*/%]");

    /**
     * 分隔符
     */
    private char separator = '#';


    @Override
    @SuppressWarnings("unchecked")
    protected RedisCache createCache(String cacheName) {
        // 获取默认时间
        long expiration = computeExpiration(cacheName);
        int index = cacheName.indexOf(this.getSeparator());
        if (index > 0) {
            expiration = getExpiration(cacheName, index, expiration);
            cacheName = cacheName.substring(0,index);
        }
        return new RedisCache(cacheName, (isUsePrefix() ? getCachePrefix().prefix(cacheName) : null),
                getRedisOperations(), expiration);
    }

    /**
     * 计算缓存时间
     * @param name 缓存名字 cache#60*60
     * @param separatorIndex 分隔符位置
     * @param defalutExp 默认缓存时间
     * @return
     */
    protected long getExpiration(final String name, final int separatorIndex, final long defalutExp) {
        Long expiration = null;
        String expirationAsString = name.substring(separatorIndex + 1);
        try {
            if (pattern.matcher(expirationAsString).find()) {
                expiration = NumberUtils.toLong(scriptEngine.eval(expirationAsString).toString(), defalutExp);
            } else {
                expiration = NumberUtils.toLong(expirationAsString, defalutExp);
            }
        } catch (ScriptException e) {
            logger.error("缓存时间转换错误:{},异常:{}", name, e.getMessage());
        }
        return Objects.nonNull(expiration) ? expiration.longValue() : defalutExp;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }
}
