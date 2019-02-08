package com.iprismech.alertnikkiresidence.pojo;

import java.util.ArrayList;
import java.util.List;

public class UsersData {

    private List<User> usersList = new ArrayList<User>() {
        {
            add(new User(0, "Notice Board", "My Society"));
            add(new User(1, "Emergency Contact", "My Society"));
            add(new User(2, "Management Committie", "My Society"));
            add(new User(3, "Family Members", "Family Members"));
            add(new User(4, "Visitors History", "Visitors History"));
            add(new User(5, "My Flat", "My Flat"));
            add(new User(6, "Digital Intervom Settings", "Digital Intervom Settings"));
            add(new User(7, "Logout", "Logout"));


        }
    };

    private List<String> userTypeList = new ArrayList<String>() {
        {
            add("My Society");
            add("Family Members");
            add("Visitors History");
            add("My Flat");
            add("Digital Intervom Settings");
            add("Logout");
        }
    };

    public List<User> getUsersList() {
        return usersList;
    }

    public List<String> getUserTypeList() {
        return userTypeList;
    }


}
