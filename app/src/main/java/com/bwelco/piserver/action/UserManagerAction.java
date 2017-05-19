package com.bwelco.piserver.action;

import com.bwelco.piserver.UserBean;
import com.bwelco.piserver.util.UserSpUtil;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/18.
 */

public class UserManagerAction extends BaseAction {

    public NanoHTTPD.Response updateToAdmin(NanoHTTPD.IHTTPSession session) {
        Map<String, String> queryMap = session.getParms();
        String username = queryMap.get("userName");
        UserSpUtil.updateToAdmin(username);
        return getDefaultResponse(true, "成功");
    }


    public NanoHTTPD.Response agreeUser(NanoHTTPD.IHTTPSession session) {
        Map<String, String> queryMap = session.getParms();
        String username = queryMap.get("userName");
        UserSpUtil.agreeUser(username);
        return getDefaultResponse(true, "成功");
    }

    public NanoHTTPD.Response getUserList(NanoHTTPD.IHTTPSession session) {
        List<UserBean> userBeen = UserSpUtil.getUserList();
        return NanoHTTPD.newFixedLengthResponse(new Gson().toJson(userBeen));
    }

    public NanoHTTPD.Response deleteUser(NanoHTTPD.IHTTPSession session) {
        Map<String, String> queryMap = session.getParms();
        String username = queryMap.get("userName");
        UserSpUtil.deleteUser(username);
        return getDefaultResponse(true, "成功");
    }
}
