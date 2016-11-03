package com.coolweather.app.coolweather.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DIY on 2016/11/2.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address,
                                       final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}


//        HttpURLConnection connection =null;
//        try {
//            URL url = new URL(address);
//            connection=(HttpURLConnection) url.openConnection();
//            ByteArrayOutputStream response = new ByteArrayOutputStream();
//            InputStream in = connection.getInputStream();
//
//            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//                throw new IOException(connection.getResponseMessage() +
//                        ":with" + address);
//            }
//
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((bytesRead = in.read(buffer)) > 0) {
//                response.write(buffer, 0, bytesRead);
//            }
//            response.close();
//            if (listener != null) {
//                listener.onFinish(response.toString());
//            }
//        } catch (Exception e) {
//            if (listener != null) {
//                listener.onError(e);
//            }
//        }finally {
//            connection.disconnect();
//        }
//    }
