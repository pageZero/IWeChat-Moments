package com.zjl.iwechatmoments.core.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zjl.iwechatmoments.core.contract.MomentsContract;
import com.zjl.iwechatmoments.core.model.TweetModel;
import com.zjl.iwechatmoments.core.model.UserInfoModel;
import com.zjl.iwechatmoments.http.i.IHttpListener;

/**
 * Created by zjl on 18-3-15.
 */

public class MomentsPresenter implements MomentsContract.Presenter{
    private static final String TAG = MomentsPresenter.class.getSimpleName();

    private MomentsContract.View mView;
    private MomentsContract.Model mUserModel;
    private MomentsContract.Model mTweetModel;
    private SharedPreferences mPreferences;
    private Context mContext;

    public MomentsPresenter(MomentsContract.View view) {
        mView = view;
        mContext = (Context) mView;
        mUserModel = new UserInfoModel(mContext);
        mTweetModel = new TweetModel(mContext);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    @Override
    public void loadUserInfo() {

    }

    @Override
    public void firstLoadTweetList() {

    }

    @Override
    public void loadMore(int lastIndex, int size) {

    }

    private IHttpListener userInfoHttpListener = new IHttpListener() {
        @Override
        public void onSuccess(String response) {

        }

        @Override
        public void onError(String e) {

        }
    };

    private IHttpListener tweetsHttpListner = new IHttpListener() {
        @Override
        public void onSuccess(String response) {

        }

        @Override
        public void onError(String e) {

        }
    };
}
