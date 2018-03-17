package com.zjl.iwechatmoments.core.model;

import com.zjl.iwechatmoments.http.entity.UserEntity;
import com.zjl.iwechatmoments.http.i.IHttpListener;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zjl on 2018/3/17.
 * Testing the function of UserInfoModel to get user detail information.
 * This is a integrated test.
 */
public class UserInfoModelTest {
    UserInfoModel userModel = new UserInfoModel();

    @Test
    public void hasCache() throws Exception {
        assertEquals(false,userModel.hasCache());// default
    }

    @Test
    public void loadAllDataFromLocal() throws Exception {
        // TODO: 2018/3/17 load data from disk or memeory
    }

    @Test
    public void loadAllDataFromRemote() throws Exception {
        userModel.loadAllDataFromRemote(new IHttpListener() {
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
        List<UserEntity> users1 =  userModel.loadData(0,1);
        assertEquals(1, users1.size());
        List<UserEntity> users2 =  userModel.loadData(0,100);
        assertEquals(1, users2.size());
    }

}