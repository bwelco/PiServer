package com.bwelco.piserver.action;

import com.bwelco.piserver.UserBean;
import com.bwelco.piserver.util.SpUtils;

import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/18.
 */

public class LoginAction extends BaseAction {

    public NanoHTTPD.Response login(NanoHTTPD.IHTTPSession session) {
        Map<String, String> queryMap = session.getParms();
        String user = queryMap.get("user");
        String passwd = queryMap.get("pass");

        List<UserBean> userList = SpUtils.getUserList();
        for (UserBean user1 : userList) {
            if (user1.passwd.equals(passwd) && user1.username.equals(user) &&
                    user1.hasPassed) {
                return getDefaultResponse(true, "登陆成功");
            }
        }

        return getDefaultResponse(false, "账号或密码不正确");
    }
}
