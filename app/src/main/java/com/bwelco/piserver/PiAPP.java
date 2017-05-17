package com.bwelco.piserver;

import android.app.Application;

import com.bwelco.piserver.server.HttpdServer;

import java.io.IOException;

/**
 * Created by bwelco on 2017/5/17.
 */

public class PiAPP extends Application {

    HttpdServer httpServerService;
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            httpServerService = new HttpdServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
