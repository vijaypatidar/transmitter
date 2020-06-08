package com.vkpapps.transmitter.interfaces;

import com.vkpapps.transmitter.ClientHelper;

import java.io.Serializable;

public interface OnClientConnectionListener<T extends Serializable> {
    void onNewClientJoin(ClientHelper<T> clientHelper);
    void onClientLeave(ClientHelper<T> clientHelper);
}
