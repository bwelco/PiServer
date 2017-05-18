package com.bwelco.piserver.action;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/18.
 */

public class BaseAction {

    class NormalResponse {
        @SerializedName("success")
        public boolean isSuccess;

        @SerializedName("reason")
        public String reason;
    }

    public NanoHTTPD.Response getDefaultResponse(boolean isSuccess, String reason) {
        NormalResponse response = new NormalResponse();
        response.isSuccess = isSuccess;
        response.reason = reason;
        return NanoHTTPD.newFixedLengthResponse(new Gson().toJson(response));
    }
}
