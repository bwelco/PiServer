package com.bwelco.piserver.util;

import android.app.Activity;
import android.content.SharedPreferences;

import com.bwelco.piserver.PiAPP;
import com.bwelco.piserver.UserBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bwelco on 2017/5/18.
 */

public class UserSpUtil {

    public final static String LOGINTABLE = "login_user_table";
    public final static String LOGINTABLE_KEY = "login_table_key";

    static SharedPreferences sharedPreferences;

    static {
        sharedPreferences = PiAPP.applicationContext.
                getSharedPreferences(LOGINTABLE, Activity.MODE_PRIVATE);
    }

    public static List<UserBean> getUserList() {
        List<UserBean> userList = new Gson().fromJson(
                sharedPreferences.getString(LOGINTABLE_KEY, "")
                , new TypeToken<List<UserBean>>() {
                }.getType());
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return userList;
    }

    public static void saveUserList(List<UserBean> list) {
        sharedPreferences.edit().putString(LOGINTABLE_KEY,
                new Gson().toJson(list)).commit();
    }

    public static void addUser(String userName, String passwd, String applyReason) {
        List<UserBean> users =  getUserList();
        for (UserBean user : users) {
            if (user.username.equals(userName)) {
                return;
            }
        }
        UserBean newUser = new UserBean();
        newUser.username = userName;
        newUser.passwd = passwd;
        newUser.applyReason = applyReason;
        newUser.hasPassed = false;
        newUser.isAdmin = false;
        users.add(newUser);
        saveUserList(users);
    }

    public static void agreeUser(String username) {
        List<UserBean> users =  getUserList();

        for (UserBean user : users) {
            if (user.username.equals(username)) {
                user.hasPassed = true;
            }
        }

        saveUserList(users);
    }

    public static void deleteUser(String userName) {
        List<UserBean> users =  getUserList();

        for (UserBean user : users) {
            if (user.username.equals(userName)) {
                users.remove(user);
                break;
            }
        }

        saveUserList(users);
    }

    public static void updateToAdmin(String userName) {
        List<UserBean> users =  getUserList();

        for (UserBean user : users) {
            if (user.username.equals(userName)) {
                user.isAdmin = true;
                break;
            }
        }

        saveUserList(users);
    }

    public static void clearUser() {
        sharedPreferences.edit().putString(LOGINTABLE_KEY, "").commit();
    }
}
