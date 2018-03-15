package com.zjl.iwechatmoments.http.i;

/**
 * Created by zjl on 18-3-15.
 */

public interface IHttpListener {
    void onSuccess(String response);
    void onError(String e);
}
