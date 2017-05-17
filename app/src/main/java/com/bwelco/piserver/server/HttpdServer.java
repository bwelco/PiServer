package com.bwelco.piserver.server;

import android.util.Log;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/17.
 */

public class HttpdServer extends NanoHTTPD {

    private static final int PORT = 8888;
    private HttpDispatcher httpDispatcher;

    public HttpdServer() throws IOException {
        super(PORT);
        httpDispatcher = new HttpDispatcher();
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {
//
//        String msg = "<html><body><h1>Hello server</h1>\n";
//        Map<String, String> parms = session.getParms();
//        if (parms.get("username") == null) {
//            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
//        } else {
//            msg += "<p>Hello, " + parms.get("username") + "!</p>";
//        }
        Log.i("admin", "url = " + session.getUri());
        return newFixedLengthResponse("{\"success\":true}");
    }
}
