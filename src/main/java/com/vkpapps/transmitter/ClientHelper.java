package com.vkpapps.transmitter;

import com.vkpapps.transmitter.interfaces.OnConnectionStatusListener;
import com.vkpapps.transmitter.interfaces.OnObjectReceiveListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

/*
 * @author VIJAY PATIDAR
 * */

public class ClientHelper<T extends Serializable> {
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final Socket socket;
    private OnObjectReceiveListener<T> onObjectReceiveListener;
    private OnConnectionStatusListener<T> onConnectionStatusListener;
    private int timeOut = 5000;
    private T user = null;

    public ClientHelper(Socket socket) {
        this.socket = socket;
        intiStreams();
        startReading();
    }

    public ClientHelper() {
        this.socket = new Socket();
    }


    public void connect(int port, String host) {
        new Thread(() -> {
            try {
                socket.connect(new InetSocketAddress(host.trim(), port), timeOut);
                intiStreams();
                outputStream.writeObject(user);
                try {
                    Thread.sleep(1600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startReading();
                if (onConnectionStatusListener!=null){
                    onConnectionStatusListener.onConnected(ClientHelper.this);
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (onConnectionStatusListener!=null){
                    onConnectionStatusListener.onFailedToConnect(ClientHelper.this);
                }
            }
        }).start();
    }

    private void intiStreams() {
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReading() {
        new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    Object o = inputStream.readObject();
                    System.out.println(o);
                    if (onObjectReceiveListener != null) {
                        onObjectReceiveListener.onObjectReceive(o, ClientHelper.this);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (onConnectionStatusListener!=null){
                onConnectionStatusListener.onDisconnected(ClientHelper.this);
            }
        }).start();
    }

    public void send(Object o) {
        if (outputStream!=null)
        new Thread(() -> {
            try {
                outputStream.writeObject(o);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    public void setOnConnectionStatusListener(OnConnectionStatusListener<T> onConnectionStatusListener) {
        this.onConnectionStatusListener = onConnectionStatusListener;
    }

    public void setOnObjectReceiveListener(OnObjectReceiveListener<T> onObjectReceiveListener) {
        this.onObjectReceiveListener = onObjectReceiveListener;
    }
}
