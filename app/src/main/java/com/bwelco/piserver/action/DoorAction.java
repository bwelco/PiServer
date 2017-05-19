package com.bwelco.piserver.action;

import com.bwelco.piserver.util.DoorSpUtil;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/19.
 */

public class DoorAction extends BaseAction {

    public NanoHTTPD.Response openDoor(NanoHTTPD.IHTTPSession session) {
        Map<String, String> queryMap = session.getParms();
        long time = Long.valueOf(queryMap.get("time"));
        String userName = queryMap.get("userName");
        DoorSpUtil.DoorEvent doorEvent = new DoorSpUtil.DoorEvent();
        doorEvent.time = time;
        doorEvent.userName = userName;
        DoorSpUtil.addDoorEvent(doorEvent);
        return getDefaultResponse(true, "成功");
    }


    public NanoHTTPD.Response closeDoor(NanoHTTPD.IHTTPSession session) {
        List<DoorSpUtil.DoorEvent> doorEvents = DoorSpUtil.getDoorEvents();
        return NanoHTTPD.newFixedLengthResponse(new Gson().toJson(doorEvents));
    }
}
