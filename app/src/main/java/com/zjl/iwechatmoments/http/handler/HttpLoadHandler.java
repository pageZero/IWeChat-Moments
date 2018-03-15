package com.zjl.iwechatmoments.http.handler;

import java.util.Map;

/**
 * Created by zjl on 18-3-15.
 * handle network request,include post and get
 */

public interface HttpLoadHandler {
    String get(String url);
    String post(String url, Map<String, String> params);
}
