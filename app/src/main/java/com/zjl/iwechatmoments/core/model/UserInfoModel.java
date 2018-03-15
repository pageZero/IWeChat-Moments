package com.zjl.iwechatmoments.core.model;

import android.content.Context;

import com.zjl.iwechatmoments.core.contract.MomentsContract;
import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.entity.UserEntity;
import com.zjl.iwechatmoments.http.i.IHttpListener;
import com.zjl.iwechatmoments.http.loader.HttpLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjl on 18-3-15.
 */

public class UserInfoModel implements MomentsContract.Model<UserEntity> {
    private static final String USERINFO_URL = "http://thoughtworks-ios.herokuapp.com/user/jsmith";

    private static UserEntity user;
    private Context mContext;

    private HttpLoader httpLoader;

    public UserInfoModel(Context context) {
        mContext = context.getApplicationContext();
        httpLoader = new HttpLoader();
    }

    @Override
    public void loadAllDataFromLocal() {
        // TODO: 18-3-15 init user
    }

    @Override
    public void loadAllDataFromRemote(IHttpListener listener) {
        httpLoader.doAsyncGet(USERINFO_URL, listener);
    }

    @Override
    public List<UserEntity> loadData(int lastIndex, int size) {
        //only one user
        List<UserEntity> users = new ArrayList<>();
        users.add(user);
        return users;
    }
}
