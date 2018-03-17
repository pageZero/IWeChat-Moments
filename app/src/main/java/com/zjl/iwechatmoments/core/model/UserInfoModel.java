package com.zjl.iwechatmoments.core.model;


import com.zjl.iwechatmoments.core.contract.MomentsContract;
import com.zjl.iwechatmoments.http.entity.UserEntity;
import com.zjl.iwechatmoments.http.i.IHttpListener;
import com.zjl.iwechatmoments.http.loader.HttpLoader;
import com.zjl.iwechatmoments.http.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjl on 18-3-15.
 */

public class UserInfoModel implements MomentsContract.Model<UserEntity> {
    private static final String USERINFO_URL = "http://thoughtworks-ios.herokuapp.com/user/jsmith";

    private static UserEntity sUser;
    private HttpLoader httpLoader;

    public UserInfoModel() {
        httpLoader = new HttpLoader();
    }

    @Override
    public boolean hasCache() {
        if (sUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public void loadAllDataFromLocal() {
        // TODO: 18-3-15 init user
    }

    @Override
    public void loadAllDataFromRemote(final IHttpListener listener) {
        httpLoader.doAsyncGet(USERINFO_URL, new IHttpListener<String, String>() {
            @Override
            public void onSuccess(String response) {
                sUser = JsonUtils.jsonToUserEntity(response);
                listener.onSuccess(sUser);
            }

            @Override
            public void onError(String e) {
                listener.onError(e);
            }
        });
    }

    @Override
    public List<UserEntity> loadData(int lastIndex, int size) {
        //only one user
        List<UserEntity> users = new ArrayList<>();
        users.add(sUser);
        return users;
    }
}
