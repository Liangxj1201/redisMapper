package com.example.demo.service;

import com.example.demo.entity.HouseUser;
import com.example.demo.mapper.HouseUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HouseUserServiceImpl implements HouseUserService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HouseUserMapper houseUserMapper;

    public HouseUser findById(String id){
        return houseUserMapper.selectById(id);
    }

    public HouseUser findByUser(HouseUser user){
        return houseUserMapper.selectByUser(user);
    }

    public int addUser(String name,String age){
        return houseUserMapper.insertHouseUser(UUID.randomUUID().toString().replace("-",""),name,age);
    }

    public void updataById(String id,String name){
        houseUserMapper.updateHouseUser(id,name);
    }

    public void deleteById(String id){
        houseUserMapper.deleteById(id);
    }

    @Override
    public List<HouseUser> selectAll() {
        return houseUserMapper.selectAll();
    }
}
