package com.technologies.smart.house.model;


public class Permissions {

    public static final int adminPermissions = 1;
    public static final int userPermissions = 2;

    public static boolean userIsAdmin(User user) {
        //TODO: get admin group from database and check user;

        return false;
    }

}
