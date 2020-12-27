package com.mesh.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mesh.backend.datas.TeamData;
import com.mesh.backend.datas.UserData;
import com.mesh.backend.entity.Admins;
import com.mesh.backend.entity.Users;
import com.mesh.backend.mapper.AdminsMapper;
import com.mesh.backend.service.IAdminsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private TeamsServiceImpl teamsService;

    @Autowired
    private ProjectsServiceImpl projectsService;

    @Autowired
    private CooperationsServiceImpl cooperationsService;

    private int getGenderCount(int gender){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Users::getGender, gender);
        return usersService.count(queryWrapper);
    }

    @Override
    public ArrayList<UserData> getUserInformationByKeyword(String keyword) {
        ArrayList<Users> users = usersService.getUserListByKeyword(keyword);
        ArrayList<UserData> userData = new ArrayList<>();
        for(Users u: users){
            ArrayList<TeamData> teamData = teamsService.getUserTeams(u.getId());
            UserData data = new UserData(u, "user",
                    cooperationsService.getPreferenceTeam(u.getId()), teamData);
            userData.add(data);
        }
        return userData;
    }

    @Override
    public int getCurrentOnlineUser() {
        //TODO: get current online user
        return 0;
    }

    @Override
    public double getAvgTeamUser() {
        return (double) cooperationsService.count() / (double) teamsService.count();
    }

    @Override
    public double getAvgTeamProject() {
        return (double) projectsService.count() / (double) teamsService.count();
    }

    @Override
    public Map<Integer, Integer> getUserAge() {
        //TODO: get user age
        return new HashMap<>();
    }

    @Override
    public Map<String, Integer> getUserLocation() {
        //TODO: get user location
        return new HashMap<>();
    }

    @Override
    public int getMaleUser() {
        return getGenderCount(1);
    }

    @Override
    public int getFemaleUser() {
        return getGenderCount(2);
    }

    @Override
    public int getUnknownUser() {
        return getGenderCount(0);
    }

    @Override
    public int getCurrentTotalUser() {
        return usersService.count();
    }

    @Override
    public ArrayList<Integer> getHistoryTotalUser(int timeInterval, int itemCount) {
        //TODO: get history total user
        return new ArrayList<>();
    }
}
