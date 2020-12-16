package com.mesh.backend.controller;


import com.mesh.backend.datas.*;
import com.mesh.backend.entity.*;
import com.mesh.backend.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aodethri
 * @since 2020-12-05
 */
@Controller
@RequestMapping("/api/mesh/knowledgebase/project")
public class ProjectmemosController {
    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private ProjectsServiceImpl projectService;

    @Autowired
    private DevelopsServiceImpl developService;

    @Autowired
    private ProjectmemosServiceImpl projectmemosService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Object createTeamKB(@RequestBody KnowledgeRequestData requestData){

        Users users = usersService.getUserByUsername(requestData.username);
        if(users == null){
            BaseData baseData = new BaseData("User status error.");
            return new BaseReturnValue(2, baseData);
        }

        Projects projects = projectService.getById(requestData.projectId);
        if(projects == null){
            BaseData baseData = new BaseData("Project does not exist.");
            return new BaseReturnValue(301, baseData);
        }

        if(!developService.checkProjectMember(projects.getId(), users.getId())){
            BaseData baseData = new BaseData("Permission denied.");
            return new BaseReturnValue(901, baseData);
        }

        Projectmemos projectmemos = projectmemosService.createNewProjectMemo(requestData, users.getId());

        if(projectmemos == null){
            BaseData baseData = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, baseData);
        }
        BaseKnowledgeData baseKnowledgeData  = new BaseKnowledgeData(projectmemos, users.getEmail());
        return new BaseReturnValue(0, baseKnowledgeData);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Object queryTeamKB(@RequestParam String username, int projectId){

        Users users = usersService.getUserByUsername(username);
        if(users == null){
            BaseData baseData = new BaseData("User status error.");
            return new BaseReturnValue(2, baseData);
        }

        Projects project = projectService.getById(projectId);
        if(project == null){
            BaseData baseData = new BaseData("Project does not exist.");
            return new BaseReturnValue(301, baseData);
        }

        if(!developService.checkProjectMember(project.getId(), users.getId())){
            BaseData baseData = new BaseData("Permission denied.");
            return new BaseReturnValue(901, baseData);
        }

        ArrayList<KnowledgeData> projectmemoList = projectmemosService.queryProjectMemo(projectId);
        if(projectmemoList == null){
            BaseData baseData = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, baseData);
        }
        BaseKnowledgeData baseKnowledgeData = new BaseKnowledgeData(projectmemoList);
        return new BaseReturnValue(0, baseKnowledgeData);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public Object deleteTeamKB(@RequestParam String username, int projectId, int knowledgeId){

        Users users = usersService.getUserByUsername(username);
        if(users == null){
            BaseData baseData = new BaseData("User status error.");
            return new BaseReturnValue(2, baseData);
        }

        Projects project = projectService.getById(projectId);
        if(project == null){
            BaseData baseData = new BaseData("Project does not exist.");
            return new BaseReturnValue(301, baseData);
        }

        Projectmemos projectmemos = projectmemosService.getById(knowledgeId);
        if(projectmemos == null){
            BaseData baseData = new BaseData("Knowledge does not exist.");
            return new BaseReturnValue(401, baseData);
        }

        if(!projectService.checkProjectAdmin(project, users.getId())&&!users.getId().equals(projectmemos.getUserId())){
            BaseData baseData = new BaseData("Permission denied.");
            return new BaseReturnValue(901, baseData);
        }

        boolean result = projectmemosService.removeById(projectmemos.getId());
        if(!result){
            BaseData baseData = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, baseData);
        }
        BaseData baseData = new BaseData(true, "");
        return new BaseReturnValue(0, baseData);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PATCH)
    public Object updateTeamKB(@RequestBody KnowledgeRequestData requestData){
        Users users = usersService.getUserByUsername(requestData.username);
        if(users == null){
            BaseData baseData = new BaseData("User status error.");
            return new BaseReturnValue(2, baseData);
        }

        Projects project = projectService.getById(requestData.projectId);
        if(project == null){
            BaseData baseData = new BaseData("Project does not exist.");
            return new BaseReturnValue(301, baseData);
        }

        Projectmemos projectmemos = projectmemosService.getById(requestData.knowledgeId);
        if(projectmemos == null){
            BaseData baseData = new BaseData("Knowledge does not exist.");
            return new BaseReturnValue(401, baseData);
        }

        if(!projectService.checkProjectAdmin(project, users.getId())&&!users.getId().equals(projectmemos.getUserId())){
            BaseData baseData = new BaseData("Permission denied.");
            return new BaseReturnValue(901, baseData);
        }

        Projectmemos updatedProjectMemo = projectmemosService.updateProjectMemo(requestData);
        if(updatedProjectMemo==null){
            BaseData baseData = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, baseData);
        }
        BaseKnowledgeData baseKnowledgeData = new BaseKnowledgeData(updatedProjectMemo,
                usersService.getById(updatedProjectMemo.getUserId()).getEmail());
        return new BaseReturnValue(0, baseKnowledgeData);
    }
}

