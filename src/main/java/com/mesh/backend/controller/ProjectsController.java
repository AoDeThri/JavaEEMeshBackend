package com.mesh.backend.controller;


import com.mesh.backend.datas.*;
import com.mesh.backend.entity.Projects;
import com.mesh.backend.entity.Teams;
import com.mesh.backend.entity.Users;
import com.mesh.backend.service.impl.ProjectsServiceImpl;
import com.mesh.backend.service.impl.TeamsServiceImpl;
import com.mesh.backend.service.impl.UsersServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/api/mesh/project")
public class ProjectsController {

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private TeamsServiceImpl teamsService;

    @Autowired
    private ProjectsServiceImpl projectsService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Object createProject(@RequestBody ProjectRequestData requestData){

        Users users = usersService.getUserByUsername(requestData.username);
        if(users == null){
            BaseData baseData = new BaseData("User status error.");
            return new BaseReturnValue(2, baseData);
        }

        Users admin = usersService.getUserByUsername(requestData.adminName);
        if(admin == null){
            BaseData baseData = new BaseData("Invalid adminName");
            return new BaseReturnValue(301, baseData);
        }

        Teams team = teamsService.getById(requestData.teamId);
        if(team == null){
            BaseData baseData = new BaseData("Team does not exist.");
            return new BaseReturnValue(201, baseData);
        }

        if(!teamsService.checkTeamAdmin(team, users.getId())){
            BaseData baseData = new BaseData("Permission denied.");
            return new BaseReturnValue(901, baseData);
        }

        Projects createResult = projectsService.createNewProject(requestData, admin.getId());
        if(createResult == null){
            BaseData baseData = new BaseData("Unexpected error.");
            return new BaseReturnValue(1, baseData);
        }

        ArrayList<Users> usersArrayList = new ArrayList<>();
        usersArrayList.add(admin);
        BaseProjectData projectData = new BaseProjectData(createResult,usersArrayList,admin.getEmail());
        return new BaseReturnValue(0, projectData);
    }
}

