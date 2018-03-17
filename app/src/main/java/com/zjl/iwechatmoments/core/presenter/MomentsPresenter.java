package com.zjl.iwechatmoments.core.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.zjl.iwechatmoments.core.contract.MomentsContract;
import com.zjl.iwechatmoments.core.model.TweetModel;
import com.zjl.iwechatmoments.core.model.UserInfoModel;
import com.zjl.iwechatmoments.core.util.ELog;
import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.entity.UserEntity;
import com.zjl.iwechatmoments.http.i.IHttpListener;
import com.zjl.iwechatmoments.http.util.JsonUtils;
import com.zjl.iwechatmoments.http.util.TweetEntityFilter;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by zjl on 18-3-15.
 * The presentecan handle logisitic control for user and tweets
 */

public class MomentsPresenter implements MomentsContract.Presenter{
    private static final String TAG = MomentsPresenter.class.getSimpleName();
    private static final int ON_USER_EVENT_FLAG = 100;
    private static final int ON_USER_ERROR_FLAG = 101;
    private static final int ON_TWEET_EVENT_FLAG = 200;
    private static final int ON_TWEET_ERROR_FLAG = 201;

    private MomentsContract.View mView;
    private MomentsContract.Model mUserModel;
    private MomentsContract.Model mTweetModel;
    private SharedPreferences mPreferences;
    private Context mContext;

    private Handler mUiHandler;

    public MomentsPresenter(MomentsContract.View view) {
        mView = view;
        mContext = (Context) mView;
        mUserModel = new UserInfoModel();
        mTweetModel = new TweetModel();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mUiHandler = new UiHandler(view);
    }

    @Override
    public void loadUserInfo() {
        if (mUserModel.hasCache()) {
            List<UserEntity> users = mUserModel.loadData(0,1);
            UserEntity user =  users.get(0);
            mView.showUserInfo(user);
        } else {
            mUserModel.loadAllDataFromRemote(userInfoHttpListener);
        }
    }

    @Override
    public void firstLoadTweetList() {
        if (mTweetModel.hasCache()) {
            List<TweetEntity> tweets = mTweetModel.loadData(0, 5);
            mView.showTweetList(tweets);
        } else {
            mTweetModel.loadAllDataFromRemote(tweetsHttpListner);
        }
    }

    @Override
    public void loadMore(int lastIndex, int size) {
        List<TweetEntity> tweets = mTweetModel.loadData(lastIndex, size);
        mView.onLoadMore(tweets);
    }

    private IHttpListener userInfoHttpListener = new IHttpListener<UserEntity, String>() {
        @Override
        public void onSuccess(UserEntity response) {
            Message msg = mUiHandler.obtainMessage(ON_USER_EVENT_FLAG, response);
            mUiHandler.sendMessage(msg);
        }

        @Override
        public void onError(String e) {
            Message msg = mUiHandler.obtainMessage(ON_USER_ERROR_FLAG, e);
            mUiHandler.sendMessage(msg);
        }
    };

    private IHttpListener tweetsHttpListner = new IHttpListener<List<TweetModel>, String>() {
        @Override
        public void onSuccess(List<TweetModel> response) {
            Message msg = mUiHandler.obtainMessage(ON_TWEET_EVENT_FLAG, response);
            mUiHandler.sendMessage(msg);
        }

        @Override
        public void onError(String e) {
            Message msg = mUiHandler.obtainMessage(ON_TWEET_ERROR_FLAG, e);
            mUiHandler.sendMessage(msg);
        }
    };

    private static class UiHandler extends Handler {
        private SoftReference<MomentsContract.View> activity;

        UiHandler(MomentsContract.View view) {
            activity = new SoftReference<MomentsContract.View>(view);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ON_USER_EVENT_FLAG:
                    if (activity.get() != null) {
                        activity.get().showUserInfo((UserEntity) msg.obj);
                    }
                    break;
                case ON_USER_ERROR_FLAG:
                    ELog.e(TAG, (String) msg.obj);
                    break;
                case ON_TWEET_EVENT_FLAG:
                    if (activity.get() != null) {
                        activity.get().showTweetList((List<TweetEntity>) msg.obj);
                    }
                    break;
                case ON_TWEET_ERROR_FLAG:
                    ELog.e(TAG, (String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    }
}
