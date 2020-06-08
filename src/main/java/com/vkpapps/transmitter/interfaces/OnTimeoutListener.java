package com.vkpapps.transmitter.interfaces;

import java.io.Serializable;

/*
 * @author VIJAY PATIDAR
 *
 * */
public interface OnTimeoutListener<T extends Serializable> {
    void onTimeout();
}
