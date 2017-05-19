package com.bwelco.piserver.action;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/19.
 */

public class DoorAction extends BaseAction {


    public NanoHTTPD.Response openDoor(NanoHTTPD.IHTTPSession session) {

        return getDefaultResponse(true, "成功");
    }


    public NanoHTTPD.Response closeDoor(NanoHTTPD.IHTTPSession session) {

        return getDefaultResponse(true, "成功");
    }
}
