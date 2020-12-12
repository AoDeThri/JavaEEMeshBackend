package com.mesh.backend.service;

import com.mesh.backend.datas.ProjectRequestData;
import com.mesh.backend.entity.Projects;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aodethri
 * @since 2020-12-05
 */
public interface IProjectsService extends IService<Projects> {

    @Transactional
    Projects createNewProject(ProjectRequestData requestData, int adminId);
}
