package com.mesh.backend.service;

import com.mesh.backend.datas.UserData;
import com.mesh.backend.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aodethri
 * @since 2020-12-05
 */
public interface IUsersService extends IService<Users> {

    Users getUserByUsername(String username);

    Users saveNewUser(UserData userData);
}
