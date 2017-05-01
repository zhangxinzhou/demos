package com.example.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * 一定要开启EnableCaching,否则缓存不生效(至少目前版本是这样)
 * @author Administrator
 *
 */
@Configuration
@EnableCaching
public class CacheConfig {

}
