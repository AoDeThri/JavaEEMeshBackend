package com.mesh.backend.datas;

import com.mesh.backend.entity.Users;

import java.time.LocalDateTime;

public class UserData extends BaseData{

    public String username;
    public String nickname;
    public int gender;
    public int status;
    public String address;
    public String description;
    public LocalDateTime birthday;
    public String avatar;
    public String role;
    public String token;
    public String password;
    public Preference preference;


    public UserData(boolean isSuccess, String msg, Users users,
                    Preference preference, String role){
        super(isSuccess, msg);
        this.preference = preference;
        this.username = users.getEmail();
        this.nickname = users.getNickname();
        this.gender = users.getGender();
        this.status = users.getStatus();
        this.address = users.getAddress();
        this.description = users.getDescription();
        this.birthday = users.getBirthday();
        this.avatar = users.getAvatar();
        this.role = role;
    }

    public UserData(){
        super(true, "");
    }
}

class Preference{
    public String preferenceShowMode;
    public String preferenceColor;
    public String preferenceLayout;
    public int preferenceTeam;


    public Preference (String preferenceColor, String preferenceShowMode,
                       String preferenceLayout, int preferenceTeam){
        this.preferenceColor = preferenceColor;
        this.preferenceLayout = preferenceLayout;
        this.preferenceTeam = preferenceTeam;
        this.preferenceShowMode = preferenceShowMode;
    }

    public Preference(){

    }
}
