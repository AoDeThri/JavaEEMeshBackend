package com.mesh.backend.controller;


import com.mesh.backend.datas.*;
import com.mesh.backend.entity.Users;
import com.mesh.backend.helper.PasswordVerifier;
import com.mesh.backend.service.impl.UsersServiceImpl;
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

    @ResponseBody
    @RequestMapping(value = "/preference/color", method = RequestMethod.POST)
    public Object preferenceColor(@RequestBody UserRequestData requestData){
        Users users = usersService.getUserByUsername(requestData.username);
        if(users == null){
            BaseData baseData = new BaseData("User status error.");
            return new BaseReturnValue(2, baseData);
        }

        boolean result = usersService.updatePreferenceColor(users, requestData.preferenceColor);
        if(!result){
            BaseData data = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, data);
        }
        BaseData data = new BaseData(true,"");
        return new BaseReturnValue(0, data);
    }

    @ResponseBody
    @RequestMapping(value = "/preference/layout", method = RequestMethod.POST)
    public Object preferenceLayout(@RequestBody UserRequestData requestData){
        Users users = usersService.getUserByUsername(requestData.username);
        if(users == null){
            BaseData baseData = new BaseData("User status error.");
            return new BaseReturnValue(2, baseData);
        }

        boolean result = usersService.updatePreferenceLayout(users, requestData.preferenceLayout);
        if(!result){
            BaseData data = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, data);
        }
        BaseData data = new BaseData(true,"");
        return new BaseReturnValue(0, data);
    }

    @ResponseBody
    @RequestMapping(value = "/preferene/show-mode", method = RequestMethod.POST)
    public Object preferenceShowMode(@RequestBody UserRequestData requestData){
        Users users = usersService.getUserByUsername(requestData.username);
        if(users == null){
            BaseData baseData = new BaseData("User status error.");
            return new BaseReturnValue(2, baseData);
        }

        boolean result = usersService.updatePreferenceShowMode(users, requestData.preferenceShowMode);
        if(!result){
            BaseData data = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, data);
        }
        BaseData data = new BaseData(true,"");
        return new BaseReturnValue(0, data);
    }
}

