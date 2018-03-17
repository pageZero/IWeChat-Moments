package com.zjl.iwechatmoments.http.i;

/**
 * Created by zjl on 18-3-15.
 */

public interface IHttpListener<T, V> {
    void onSuccess(T response);
    void onError(V e);
}
