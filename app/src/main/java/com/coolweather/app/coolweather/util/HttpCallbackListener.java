package com.coolweather.app.coolweather.util;

/**
 * Created by DIY on 2016/11/2.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
