package com.mesh.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.mesh.backend.datas.BaseData;
import com.mesh.backend.datas.BaseReturnValue;
import com.mesh.backend.datas.PasswordData;
import com.mesh.backend.datas.UserData;
import com.mesh.backend.entity.Users;
import com.mesh.backend.helper.PasswordVerifier;
import com.mesh.backend.service.impl.UsersServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.StyledEditorKit;

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
        if(user != null){
            BaseData data = new BaseData("User already exists.");
            return new BaseReturnValue(101, data);
        }
        Users result = usersService.saveNewUser(userData);
        if(result != null){
            return new BaseReturnValue(0, new UserData(result, "user", -1));
        }else{
            BaseData data = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, data);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody UserData userData){
        Users user = usersService.getUserByUsername(userData.username);
        if(user == null){
            BaseData data = new BaseData("Invalid username or password.");
            return new BaseReturnValue(102, data);
        }

        boolean loginResult = PasswordVerifier.verify(userData.password,
                new PasswordData(user.getPasswordDigest(), user.getPasswordSalt()));
        if(!loginResult){
            BaseData data = new BaseData("Invalid username or password.");
            return new BaseReturnValue(102, data);
        }

        //TODO: confirm and keep login status
        //TODO: preferenceTeam
        UserData data = new UserData(user, "user", -1);
        return new BaseReturnValue(0, data);
    }
}

