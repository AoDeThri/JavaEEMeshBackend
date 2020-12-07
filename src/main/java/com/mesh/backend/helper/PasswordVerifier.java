package com.mesh.backend.helper;

import com.mesh.backend.datas.PasswordData;

public class PasswordVerifier {

    //TODO: verify password
    public static boolean verify(String password, PasswordData passwordData){
        return password.equals(passwordData.passwordDigest);
    }

    //TODO: encryption
    public static PasswordData encryption(String password){
        return new PasswordData(password, password);
    }

}
