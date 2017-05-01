package com.example.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.User;

@CacheConfig(cacheNames="users")
public interface UserRepository extends JpaRepository<User, Long>{

    @Cacheable(key = "#p0")
    User findByName(String name);
  
    //目的，在保存更新user的时候更新缓存
    @SuppressWarnings("unchecked")
	@CachePut(key = "#p0.name")
    User save(User user);
	
	/*
	 * 简单总结：
	 * @CacheConfig(cacheNames = "users")：主要用于配置该类中会用到的一些共用的缓存配置，
	 *                         cacheNames配置了该数据访问对象中返回的内容将存储于名为users的缓存对象中
	 * @Cacheable：配置了findByName函数的返回值将被加入缓存。同时在查询时，会先从缓存中获取，若不存在才再发起对数据库的访问。
	 * @CachePut：配置于函数上，能够根据参数定义条件来进行缓存，它与@Cacheable不同的是，它每次都会真是调用函数，所以主要用于数据新增和修改操作上
	 * @CacheEvict：配置于函数上，通常用在删除方法上，用来从缓存中移除相应数据
	 */
}
