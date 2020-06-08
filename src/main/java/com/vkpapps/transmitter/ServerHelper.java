package com.vkpapps.transmitter;

import com.vkpapps.transmitter.interfaces.OnConnectionStatusListener;
import com.vkpapps.transmitter.interfaces.OnObjectReceiveListener;
import com.vkpapps.transmitter.interfaces.OnClientConnectionListener;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * @author VIJAY PATIDAR
 *
 * */

public class ServerHelper<T extends Serializable> implements OnConnectionStatusListener<T> {

    private final List<ClientHelper<T>> connectedClients;
    private ServerSocket serverSocket;
    private final OnObjectReceiveListener<T> onObjectReceiveListener;
    private final OnClientConnectionListener<T> connectionStatusListener;
    private final int port;

    private T user;

    public ServerHelper(int port,
                        OnObjectReceiveListener<T> onObjectReceiveListener,
                        OnClientConnectionListener<T> onClientConnectionListener
    ) {
        this.onObjectReceiveListener = onObjectReceiveListener;
        this.connectionStatusListener = onClientConnectionListener;
        this.connectedClients = new ArrayList<>();
        this.port = port;
    }

    public void startServer() {
        new Thread(() -> {
            try {
                if (serverSocket == null) {
                    serverSocket = new ServerSocket(port);
                }
                while (true) {
                    addClient();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void startServerForOneClient() {
        new Thread(() -> {
            try {
                if (serverSocket == null) {
                    serverSocket = new ServerSocket(port);
                }
                addClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void addClient() {
        try {
            Socket accept = serverSocket.accept();
            ClientHelper<T> clientHelper = new ClientHelper<>(accept);
            clientHelper.setOnObjectReceiveListener(onObjectReceiveListener);
            clientHelper.setOnConnectionStatusListener(this);
            onConnected(clientHelper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void broadcastObject(Object o) {
        for (ClientHelper<T> clientHelper : connectedClients) {
            clientHelper.send(o);
        }
    }

    public void sendObjectTo(ClientHelper<T> clientHelper, Object o) {
        clientHelper.send(o);
    }


    @Override
    public void onConnected(ClientHelper<T> clientHelper) {
        connectedClients.add(clientHelper);

        if (connectionStatusListener != null) {
            connectionStatusListener.onNewClientJoin(clientHelper);
        }
    }

    @Override
    public void onFailedToConnect(ClientHelper<T> clientHelper) {

    }

    @Override
    public void onDisconnected(ClientHelper<T> clientHelper) {
        connectedClients.remove(clientHelper);
        if (connectionStatusListener != null) {
            connectionStatusListener.onClientLeave(clientHelper);
        }
    }

    public List<ClientHelper<T>> getConnectedClients() {
        return connectedClients;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }
}
