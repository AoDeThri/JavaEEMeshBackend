package com.mesh.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.mesh.backend.datas.BaseData;
import com.mesh.backend.datas.BaseReturnValue;
import com.mesh.backend.datas.UserData;
import com.mesh.backend.entity.Users;
import com.mesh.backend.service.impl.UsersServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aodethri
 * @since 2020-12-05
 */
@Controller
@RequestMapping("/api/mesh")
public class UsersController {

    @Autowired
    private UsersServiceImpl usersService;

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody UserData userData){
        Users user = usersService.getUserByUsername(userData.username);
        if(user !=null){
            BaseData data = new BaseData("User already exists.");
            return new BaseReturnValue(101, data);
        }
        boolean result = usersService.saveNewUser(userData);
        if(result){
            return new BaseReturnValue(0, userData);
        }else{
            BaseData data = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, data);
        }
    }
}

