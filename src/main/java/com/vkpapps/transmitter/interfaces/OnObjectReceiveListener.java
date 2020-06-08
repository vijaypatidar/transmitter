package com.vkpapps.transmitter.interfaces;

import com.vkpapps.transmitter.ClientHelper;

import java.io.Serializable;

/*
 * @author VIJAY PATIDAR
 *
 */

public interface OnObjectReceiveListener<T extends Serializable> {
    void onObjectReceive(Object object, ClientHelper<T> source);
}
