package com.example.demo.mapper;

import com.example.demo.entity.HouseUser;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface HouseUserMapper {

    int insertHouseUser(@Param("id")String id,@Param("name")String name,@Param("age")String age);

    @Cacheable(value = "house:user#4*60",key ="'house:user:'+#p0")
    public HouseUser selectById(@Param("id") String id);

    @Cacheable(value = "house:user#10*60",key ="'house:user:'+#p0.toMd5()")
    public HouseUser selectByUser(@Param("id") HouseUser user);

    @CachePut(key = "#p0")
    public void updateHouseUser(@Param("id")String id,@Param("name")String name);

    //如果指定为 true，则方法调用后将立即清空所有缓存  （,allEntries=true）
    @CacheEvict(key ="#p0")
    public void deleteById(@Param("id")String id);

    public List<HouseUser> selectAll();
}