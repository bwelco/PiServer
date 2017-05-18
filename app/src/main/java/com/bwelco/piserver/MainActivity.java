package com.bwelco.piserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwelco.piserver.socket.DoorController;
import com.bwelco.piserver.socket.SocketService;
import com.bwelco.piserver.util.SpUtils;

public class MainActivity extends AppCompatActivity {

    private static final int PORT = 1024;
    SocketService socketService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socketService = SocketService.service(PORT);

        DoorController.getInstance().reset();

        SpUtils.clearUser();
    }


    @Override
    protected void onStop() {
        super.onStop();
        DoorController.getInstance().close();
    }
}
