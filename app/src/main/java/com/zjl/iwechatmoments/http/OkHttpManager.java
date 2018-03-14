package com.zjl.iwechatmoments.http;

/**
 * Created by zjl on 18-3-14.
 */

public class OkHttpManager {

    private OkHttpManager() {

    }

    public OkHttpManager getInstance() {
        return HttpManagerHolder.INSTANCE;
    }

    private static class HttpManagerHolder {
        private static final OkHttpManager INSTANCE = new OkHttpManager();
    }
}
