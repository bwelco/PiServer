package com.bwelco.piserver.server;

import com.bwelco.piserver.action.DoorAction;
import com.bwelco.piserver.action.ExceptionAction;
import com.bwelco.piserver.action.LoginAction;
import com.bwelco.piserver.action.RegisterAction;
import com.bwelco.piserver.action.UserManagerAction;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by bwelco on 2017/5/17.
 */

public class HttpDispatcher {

    interface LoginUri {
        String login = "/login";
        String register = "/register";
    }

    interface UserManagerUri {
        String getUserList = "/getUserList";
        String upToAdmin = "/updateUserToAdmin";
        String agreeApply = "/agreeApply";
        String deleteUser = "/deleteUser";
    }

    interface DoorControllerUri {
        String openDoor = "/openDoor";
        String closeDoor = "/closeDoor";
        String getEventList = "/getDoorEventList";
    }

    interface ExceptionUri {
        String loginPatternErr = "/exception/loginFail";
        String superPatternErr = "/exception/trySpuerAdminFail";
        String getExceptionList = "/exception/getExceptionList";
    }


    public NanoHTTPD.Response dispatch(NanoHTTPD.IHTTPSession session){
        String uri = session.getUri();

        if (uri.equals(LoginUri.login)){
            return new LoginAction().login(session);
        } else if (uri.equals(LoginUri.register)) {
            return new RegisterAction().register(session);
        } else if (uri.equals(UserManagerUri.getUserList)) {
            return new UserManagerAction().getUserList(session);
        } else if (uri.equals(UserManagerUri.upToAdmin)) {
            return new UserManagerAction().updateToAdmin(session);
        } else if (uri.equals(UserManagerUri.agreeApply)) {
            return new UserManagerAction().agreeUser(session);
        } else if (uri.equals(UserManagerUri.deleteUser)) {
            return new UserManagerAction().deleteUser(session);
        } else if (uri.equals(DoorControllerUri.openDoor)) {
            return new DoorAction().openDoor(session);
        } else if (uri.equals(DoorControllerUri.closeDoor)) {
            return new DoorAction().closeDoor(session);
        } else if (uri.equals(DoorControllerUri.getEventList)) {
            return new DoorAction().getDoorEventList(session);
        } else if (uri.equals(ExceptionUri.loginPatternErr)) {
            return new ExceptionAction().userPatternNotCorrect(session);
        } else if (uri.equals(ExceptionUri.superPatternErr)) {
            return new ExceptionAction().trySuperPattern(session);
        } else if (uri.equals(ExceptionUri.getExceptionList)) {
            return new ExceptionAction().getExceptionList(session);
        }

        return NanoHTTPD.newFixedLengthResponse("error");
    }
}
