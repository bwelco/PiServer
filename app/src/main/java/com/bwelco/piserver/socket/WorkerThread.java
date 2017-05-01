package com.bwelco.piserver.socket;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by bwelco on 2017/4/30.
 */

public class WorkerThread extends Thread {

    private byte[] buffer = new byte[256];

    private Socket socket;

    public WorkerThread(Socket socket) {
        Log.i("admin", "connect :" + socket.getInetAddress());
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                int len = inputStream.read(buffer);
                if (len != -1) {
                    dealWithMessage(new String(buffer, 0, len));
                } else {
                    SocketService.getInstance().closeConnection(socket);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void dealWithMessage(String message) {
        ActionModel actionModel = new Gson().fromJson(message, ActionModel.class);
        if (actionModel.action.equals(Actions.OPEN_DOOR)) {
            doOpenDoor();
        } else if (actionModel.action.equals(Actions.CLOSE_DOOR)) {
            doCloseDoor();
        }
    }

    public void doOpenDoor() {
        DoorController.getInstance().setDegree(80);
    }

    public void doCloseDoor() {
        DoorController.getInstance().reset();
    }
}
