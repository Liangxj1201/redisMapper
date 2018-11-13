package com.example.demo.service;

import com.example.demo.entity.HouseUser;

import java.util.List;

public interface HouseUserService {
    HouseUser findById(String id);

    HouseUser findByUser(HouseUser user);

    public int addUser(String name,String age);

    public void updataById(String id,String name);

    public void deleteById(String id);

    public List<HouseUser> selectAll();
}
