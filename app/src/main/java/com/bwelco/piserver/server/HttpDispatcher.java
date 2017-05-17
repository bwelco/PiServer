package com.bwelco.piserver.server;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/17.
 */

public class HttpDispatcher {

    private static final String LOGIN = "login";

    public void dispatch(NanoHTTPD.IHTTPSession session){

        if (session.getUri().equals(LOGIN)){

        }
    }
}
