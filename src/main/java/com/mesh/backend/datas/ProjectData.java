package com.mesh.backend.datas;

import com.mesh.backend.entity.Projects;
import com.mesh.backend.entity.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProjectData {
    public int projectId;
    public LocalDateTime createTime;
    public String projectName;
    public String adminName;
    public boolean isPublic;
    ArrayList<MemberInfo> members;

    public ProjectData(Projects projects, List<Users> users, String adminName){
        this.adminName = adminName;
        this.projectId = projects.getId();
        this.createTime = projects.getCreatedTime();
        this.projectName = projects.getName();
        this.isPublic = projects.getPublicity();
        this.members = new ArrayList<>();
        for(Users u: users){
            this.members.add(new MemberInfo(u.getEmail(), u.getNickname()));
        }
    }

    public ProjectData(){

    }
}
