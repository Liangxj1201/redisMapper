package com.example.demo.controller;

import com.example.demo.entity.HouseUser;
import com.example.demo.service.HouseUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseUserController {

    @Autowired
    private HouseUserServiceImpl houseUserService;

    @RequestMapping("/users")
    public List<HouseUser> getUsers(){
        List<HouseUser> users=houseUserService.selectAll();
        return users;
    }

    @RequestMapping("/user/{id}")
    public HouseUser getUser(@PathVariable String id){
        HouseUser user=houseUserService.findById(id);
        return user;
    }
    @RequestMapping("/user")
    public HouseUser getUser(HouseUser user){
        HouseUser users=houseUserService.findByUser(user);
        return users;
    }

    @RequestMapping("/addUser")
    public String putUser(HouseUser user){
        String str = "T";
        houseUserService.addUser(user.getName(),user.getAge());
        return str;
    }


}
