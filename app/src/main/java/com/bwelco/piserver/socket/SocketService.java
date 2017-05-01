package com.bwelco.piserver.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by bwelco on 2017/4/30.
 */

public class SocketService {

    private ArrayList<Socket> clients;
    private ServerSocket serverSocket;
    private static SocketService socketService;

    public void closeConnection(Socket socket) {
        clients.remove(socket);
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SocketService(final int port) {
        clients = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);
                    while (true) {
                        clients.add(serverSocket.accept());
                        WorkerThread workerThread = new WorkerThread
                                (clients.get(clients.size() - 1));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static SocketService service(int port) {
        if (null == socketService) {
            synchronized (SocketService.class) {
                if (null == socketService) {
                    socketService = new SocketService(port);
                }
            }
        }

        return socketService;
    }

    public static SocketService getInstance() {
        return socketService;
    }

}
