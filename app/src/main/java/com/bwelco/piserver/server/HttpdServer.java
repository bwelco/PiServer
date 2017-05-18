package com.bwelco.piserver.server;

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
        return httpDispatcher.dispatch(session);
    }
}
