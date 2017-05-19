package com.bwelco.piserver.action;

import com.bwelco.piserver.util.ExceptionSPUtil;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/19.
 */

public class ExceptionAction extends BaseAction {


    public NanoHTTPD.Response userPatternNotCorrect(NanoHTTPD.IHTTPSession session) {
        Map<String, String> queryParams = session.getParms();

        String userName = queryParams.get("userName");
        long time = Long.valueOf(queryParams.get("time"));

        ExceptionSPUtil.ExceptionBean exceptionBean = new ExceptionSPUtil.ExceptionBean();
        exceptionBean.errType = "尝试手势密码解锁失败";
        exceptionBean.userName = userName;
        exceptionBean.time = time;
        ExceptionSPUtil.addExceptionEvent(exceptionBean);
        return getDefaultResponse(true, "成功");
    }

    public NanoHTTPD.Response trySuperPattern(NanoHTTPD.IHTTPSession session) {
        Map<String, String> queryParams = session.getParms();
        long time = Long.valueOf(queryParams.get("time"));
        ExceptionSPUtil.ExceptionBean exceptionBean = new ExceptionSPUtil.ExceptionBean();
        exceptionBean.errType = "尝试超级管理员手势密码失败";
        exceptionBean.time = time;
        ExceptionSPUtil.addExceptionEvent(exceptionBean);
        return getDefaultResponse(true, "成功");
    }


    public NanoHTTPD.Response getExceptionList(NanoHTTPD.IHTTPSession session) {
        List<ExceptionSPUtil.ExceptionBean> list = ExceptionSPUtil.getExceptionList();
        return NanoHTTPD.newFixedLengthResponse(new Gson().toJson(list));
    }
}
