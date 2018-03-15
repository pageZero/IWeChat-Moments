package com.zjl.iwechatmoments.http.handler;

import com.zjl.iwechatmoments.http.constant.HttpState;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by zjl on 18-3-15.
 */

public class HttpUrlConnectHandler implements HttpLoadHandler {
    @Override
    public String get(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() != HttpState.OK) {
                return null;
            }
            InputStream in = connection.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
            return out.toString("utf-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String post(String url, Map<String, String> params) {
        // TODO: 18-3-15 add post
        return null;
    }
}
