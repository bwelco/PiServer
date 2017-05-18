package com.bwelco.piserver.server;

import com.bwelco.piserver.action.LoginAction;
import com.bwelco.piserver.action.RegisterAction;
import com.bwelco.piserver.action.UserManagerAction;

import java.util.Arrays;
import java.util.List;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/17.
 */

public class HttpDispatcher {

    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";

    private static final List<String> USERMANAGER =
            Arrays.asList("/getUserList",
                    "/updateUserToAdmin",
                    "/agreeApply",
                    "/deleteUser");


    public NanoHTTPD.Response dispatch(NanoHTTPD.IHTTPSession session){
        String uri = session.getUri();

        if (uri.equals(LOGIN)){
            return new LoginAction().login(session);
        } else if (uri.equals(REGISTER)) {
            return new RegisterAction().register(session);
        } else if (uri.equals(USERMANAGER.get(0))) {
            return new UserManagerAction().getUserList(session);
        } else if (uri.equals(USERMANAGER.get(1))) {
            return new UserManagerAction().updateToAdmin(session);
        } else if (uri.equals(USERMANAGER.get(2))) {
            return new UserManagerAction().agreeUser(session);
        } else if (uri.equals(USERMANAGER.get(3))) {
            return new UserManagerAction().deleteUser(session);
        }

        return NanoHTTPD.newFixedLengthResponse("error");
    }
}
