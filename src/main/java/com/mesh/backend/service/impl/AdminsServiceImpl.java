package com.mesh.backend.service.impl;

import com.mesh.backend.entity.Admins;
import com.mesh.backend.mapper.AdminsMapper;
import com.mesh.backend.service.IAdminsService;
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
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins> implements IAdminsService {

}
