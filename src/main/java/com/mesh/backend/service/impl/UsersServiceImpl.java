package com.mesh.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mesh.backend.datas.PasswordData;
import com.mesh.backend.datas.UserData;
import com.mesh.backend.entity.Users;
import com.mesh.backend.helper.PasswordVerifier;
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

    @Override
    public Users saveNewUser(UserData userData) {
        Users user = new Users();
        user.setEmail(userData.username);
        PasswordData passwordData = PasswordVerifier.encryption(userData.password);
        user.setPasswordDigest(passwordData.passwordDigest);
        user.setPasswordSalt(passwordData.passwordSalt);
        user.setNickname(userData.username);
        boolean result = save(user);
        return result? user : null;
    }

    @Override
    public boolean updatePreferenceColor(Users user, String color) {
        user.setColorPreference(color);
        return updateById(user);
    }

    @Override
    public boolean updatePreferenceShowMode(Users user, String showMode) {
        user.setRevealedPreference(showMode);
        return updateById(user);
    }

    @Override
    public boolean updatePreferenceLayout(Users user, String layout) {
        user.setLayoutPreference(layout);
        return updateById(user);
    }
}
