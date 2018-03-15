package com.zjl.iwechatmoments.core.contract;

import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.entity.UserEntity;
import com.zjl.iwechatmoments.http.i.IHttpListener;

import java.util.List;

/**
 * Created by zjl on 18-3-15.
 */

public interface MomentsContract {
    interface Model<T> {
        void loadAllDataFromLocal();
        void loadAllDataFromRemote(IHttpListener listener);
        List<T> loadData(int lastIndex, int size);
    }
    interface View {
        void showUserInfo(UserEntity user);
        void showTweetList(List<TweetEntity> tweets);
        void refresh();
        void loadMore();
    }
    interface Presenter {
        void loadUserInfo();
        void firstLoadTweetList();
        void loadMore(int lastIndex, int size);
    }
}
