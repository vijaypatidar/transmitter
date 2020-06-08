package com.vkpapps.transmitter.interfaces;

import com.vkpapps.transmitter.ClientHelper;

import java.io.Serializable;

/*
 * @author VIJAY PATIDAR
 * */

public interface OnConnectionStatusListener<T extends Serializable> {
    void onConnected(ClientHelper<T> clientHelper);
    void onFailedToConnect(ClientHelper<T> clientHelper);
    void onDisconnected(ClientHelper<T> clientHelper);
}
