package com.mesh.backend.service.impl;

import com.mesh.backend.entity.Teams;
import com.mesh.backend.mapper.TeamsMapper;
import com.mesh.backend.service.ITeamsService;
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
public class TeamsServiceImpl extends ServiceImpl<TeamsMapper, Teams> implements ITeamsService {

}