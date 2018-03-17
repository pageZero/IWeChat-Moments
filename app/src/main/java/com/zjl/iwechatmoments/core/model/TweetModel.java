package com.zjl.iwechatmoments.core.model;

import android.content.Context;

import com.zjl.iwechatmoments.core.contract.MomentsContract;
import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.i.IHttpListener;
import com.zjl.iwechatmoments.http.loader.HttpLoader;
import com.zjl.iwechatmoments.http.util.JsonUtils;
import com.zjl.iwechatmoments.http.util.TweetEntityFilter;

import java.util.List;

/**
 * Created by zjl on 18-3-15.
 */

public class TweetModel implements MomentsContract.Model<TweetEntity>{
    private static final String TWEET_URL = "http://thoughtworks-ios.herokuapp.com/user/jsmith/tweets";
    //tweets list load in memory
    private static List<TweetEntity> sTweetList;

    private HttpLoader httpLoader;
    // TODO: 18-3-15 local loader

    public TweetModel() {
        httpLoader = new HttpLoader();
    }

    @Override
    public boolean hasCache() {
        if (sTweetList == null || sTweetList.size() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void loadAllDataFromLocal() {
        // TODO: 18-3-15 init sTweetList 
    }

    @Override
    public void loadAllDataFromRemote(final IHttpListener listener) {
        httpLoader.doAsyncGet(TWEET_URL, new IHttpListener<String, String>() {
            @Override
            public void onSuccess(String response) {
                addToCache(response);
                listener.onSuccess(sTweetList.subList(0,5));
            }

            @Override
            public void onError(String e) {
                listener.onError(e);
            }
        });
    }

    private void addToCache(String response) {
        sTweetList = new TweetEntityFilter().filter(JsonUtils.jsonToTweets(response));
    }

    /*
      get subList, from lastIndex
     */
    @Override
    public List<TweetEntity> loadData(int lastIndex, int size) {
        if (sTweetList == null || sTweetList.size() == 0)
            return null;
        if (lastIndex >= (sTweetList.size() - 1) || lastIndex < 0)
            return null;
        if ((lastIndex + size) > sTweetList.size() )
            return sTweetList.subList(lastIndex, sTweetList.size());
        return sTweetList.subList(lastIndex, lastIndex + size);
    }
}
