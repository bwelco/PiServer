package com.bwelco.piserver.action;

import com.bwelco.piserver.UserBean;
import com.bwelco.piserver.util.SpUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/18.
 */

public class LoginAction extends BaseAction {


    class LoginResponse {
        @SerializedName("username")
        public String userName;

        @SerializedName("passwd")
        public String passwd;

        @SerializedName("isAdmin")
        public boolean isAdmin;

        @SerializedName("hasPassed")
        public boolean hasPassed;

        @SerializedName("isSuccess")
        public boolean isSuccess;

        @SerializedName("reason")
        public String reason;
    }


    public NanoHTTPD.Response login(NanoHTTPD.IHTTPSession session) {
        Map<String, String> queryMap = session.getParms();
        String user = queryMap.get("user");
        String passwd = queryMap.get("pass");

        List<UserBean> userList = SpUtils.getUserList();

        LoginResponse loginResponse = new LoginResponse();

        for (UserBean user1 : userList) {
            if (user1.username.equals(user)) {
                if (user1.passwd.equals(passwd)) {
                    if (user1.hasPassed) {
                        loginResponse.isAdmin = user1.isAdmin;
                        loginResponse.userName = user1.username;
                        loginResponse.passwd = user1.passwd;
                        loginResponse.hasPassed = user1.hasPassed;

                        loginResponse.isSuccess = true;
                        loginResponse.reason = "登录成功";

                        return NanoHTTPD.newFixedLengthResponse(new Gson().toJson(loginResponse));
                    } else {
                        loginResponse.isSuccess = false;
                        loginResponse.reason = "你还未通过审核";

                        return NanoHTTPD.newFixedLengthResponse(new Gson().toJson(loginResponse));
                    }
                } else {
                    loginResponse.isSuccess = false;
                    loginResponse.reason = "密码错误";

                    return NanoHTTPD.newFixedLengthResponse(new Gson().toJson(loginResponse));
                }
            }
        }

        loginResponse.isSuccess = false;
        loginResponse.reason = "账号不存在";

        return NanoHTTPD.newFixedLengthResponse(new Gson().toJson(loginResponse));
    }
}
