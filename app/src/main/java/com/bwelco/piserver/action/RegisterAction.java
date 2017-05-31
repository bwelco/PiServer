package com.bwelco.piserver.action;

import com.bwelco.piserver.UserBean;
import com.bwelco.piserver.util.UserSpUtil;

import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/18.
 */

public class RegisterAction extends BaseAction {

    public NanoHTTPD.Response register(NanoHTTPD.IHTTPSession session) {
        Map<String, String> queryMap = session.getParms();
        String username = queryMap.get("user");
        String passwd = queryMap.get("pass");
        String applyReason = queryMap.get("apply_info");

        List<UserBean> users = UserSpUtil.getUserList();
        for (UserBean userBean : users) {
            if (userBean.username.equals(username)) {
                return getDefaultResponse(false, "用户名已存在");
            }
        }

        UserSpUtil.addUser(username, passwd, applyReason);
        return getDefaultResponse(true, "成功发送申请");
    }

}
