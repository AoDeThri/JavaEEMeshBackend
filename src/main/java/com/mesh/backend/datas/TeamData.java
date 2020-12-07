package com.mesh.backend.datas;

import com.mesh.backend.entity.Teams;
import com.mesh.backend.entity.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TeamData{
    public int teamId;
    public LocalDateTime createTime;
    public String teamName;
    public String adminName;
    ArrayList<MemberInfo> members;
    //TODO: INFO:teamProjects


    public TeamData(Teams teams, List<Users> users, String adminName){
        this.teamId = teams.getId();
        this.createTime = teams.getCreatedTime();
        this.teamName = teams.getName();
        this.adminName = adminName;
        this.members = new ArrayList<>();
        for (Users user:users) {
            members.add(new MemberInfo(user.getEmail(), user.getNickname()));
        }
    }

    public TeamData(){

    }
}

class MemberInfo{
    public String username;
    public String nickname;

    public MemberInfo(String username, String nickname){
        this.nickname = nickname;
        this.username = username;
    }
}