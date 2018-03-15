package com.zjl.iwechatmoments.core.model;

import android.content.Context;

import com.zjl.iwechatmoments.core.contract.MomentsContract;
import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.i.IHttpListener;
import com.zjl.iwechatmoments.http.loader.HttpLoader;

import java.util.List;

/**
 * Created by zjl on 18-3-15.
 */

public class TweetModel implements MomentsContract.Model<TweetEntity>{
    private static final String TWEET_URL = "http://thoughtworks-ios.herokuapp.com/user/jsmith/tweets";

    private static List<TweetEntity> sTweetList;
    private Context mContext;

    private HttpLoader httpLoader;
    // TODO: 18-3-15 local loader

    public TweetModel(Context context) {
        mContext = context.getApplicationContext();
        httpLoader = new HttpLoader();
    }

    @Override
    public void loadAllDataFromLocal() {
        // TODO: 18-3-15 init sTweetList 
    }

    @Override
    public void loadAllDataFromRemote(IHttpListener listener) {
        httpLoader.doAsyncGet(TWEET_URL, listener);
    }

    //返回从lastIndex开始，大小为size的数据列表
    @Override
    public List<TweetEntity> loadData(int lastIndex, int size) {
        if (lastIndex >= (sTweetList.size() - 1) || lastIndex < 0)
            return null;
        if ((lastIndex + size) > sTweetList.size() )
            return sTweetList.subList(lastIndex, sTweetList.size());
        return sTweetList.subList(lastIndex, lastIndex + size);
    }
}
