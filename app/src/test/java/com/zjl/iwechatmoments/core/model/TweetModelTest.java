package com.zjl.iwechatmoments.core.model;

import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.i.IHttpListener;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zjl on 2018/3/17.
 */
public class TweetModelTest {

    TweetModel tweetModel = new TweetModel();
    @Test
    public void hasCache() throws Exception {
        boolean hasMemeoryCache = tweetModel.hasCache();
        assertEquals(false, hasMemeoryCache);
    }

    @Test
    public void loadAllDataFromLocal() throws Exception {
        // TODO: 2018/3/17 load data from disk or memory
    }

    @Test
    public void loadAllDataFromRemote() throws Exception {
        tweetModel.loadAllDataFromRemote(new IHttpListener() {
            @Override
            public void onSuccess(Object response) {
                assertTrue(response != null);
            }

            @Override
            public void onError(Object e) {
                assertTrue(e == null);
            }
        });
    }

    @Test
    public void loadData() throws Exception {
        tweetModel.loadAllDataFromRemote(new IHttpListener() {
            @Override
            public void onSuccess(Object response) {
                assertTrue(response != null);

                List<TweetEntity> tweets = tweetModel.loadData(0, 1);
                assertTrue(tweets != null);
            }

            @Override
            public void onError(Object e) {
                assertTrue(e == null);
            }
        });
    }

}