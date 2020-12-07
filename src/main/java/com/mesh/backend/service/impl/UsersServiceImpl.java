package com.mesh.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mesh.backend.datas.UserData;
import com.mesh.backend.entity.Users;
import com.mesh.backend.mapper.UsersMapper;
import com.mesh.backend.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aodethri
 * @since 2020-12-05
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Override
    public Users getUserByUsername(String username) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Users::getEmail, username);
        return getOne(queryWrapper, false);
    }

    //TODO: add encryption algorithm
    @Override
    public boolean saveNewUser(UserData userData) {
        Users user = new Users();
        user.setEmail(userData.username);
        user.setPasswordDigest(userData.password);
        user.setPasswordSalt(userData.password);
        user.setNickname(userData.username);
        return save(user);
    }
}
