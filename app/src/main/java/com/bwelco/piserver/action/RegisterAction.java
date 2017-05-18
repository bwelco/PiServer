package com.bwelco.piserver.action;

import com.bwelco.piserver.util.SpUtils;

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
        SpUtils.addUser(username, passwd, applyReason);
        return getDefaultResponse(true, "成功发送申请");
    }

}
