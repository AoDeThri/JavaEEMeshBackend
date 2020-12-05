package com.mesh.backend.service.impl;

import com.mesh.backend.entity.Tasks;
import com.mesh.backend.mapper.TasksMapper;
import com.mesh.backend.service.ITasksService;
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
public class TasksServiceImpl extends ServiceImpl<TasksMapper, Tasks> implements ITasksService {

}
