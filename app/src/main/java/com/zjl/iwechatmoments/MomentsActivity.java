package com.zjl.iwechatmoments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zjl.iwechatmoments.core.adapter.EntityAdapter;
import com.zjl.iwechatmoments.core.contract.MomentsContract;
import com.zjl.iwechatmoments.core.presenter.MomentsPresenter;
import com.zjl.iwechatmoments.core.view.EnhanceRecyclerView;
import com.zjl.iwechatmoments.core.view.MyItemDecoration;
import com.zjl.iwechatmoments.http.entity.Entity;
import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * show the Moments,depend on Presenter to get data
 */

public class MomentsActivity extends AppCompatActivity implements MomentsContract.View{
    private static final String TAG = MomentsActivity.class.getSimpleName();
    private MomentsContract.Presenter presenter;
    private Context mContext;

    private EnhanceRecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private UserEntity mUser;
    private List<TweetEntity> mTweetList = new ArrayList<>();
    private static final List<Entity> mEntityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments);
        mContext = this;
        presenter = new MomentsPresenter(this);
        mTweetList.clear();
        mEntityList.clear();
        requestPermission();
        initViews();
        initAdapter();
        initListener();
        initDatas();
    }

    private void initListener() {
        mRecycleView.setPullToRefreshListener(new EnhanceRecyclerView.PullToRefreshListener() {
            @Override
            public void onRefreshing() {
                presenter.firstLoadTweetList();
            }
        });
        mRecycleView.setLoadMoreListener(new EnhanceRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore(mEntityList.size() - 1, 5);
            }
        });
    }

    private void initAdapter() {
        mAdapter = new EntityAdapter(mEntityList, mContext);
        mRecycleView.setAdapter(mAdapter);
    }

    private void initViews() {
        mRecycleView = (EnhanceRecyclerView) findViewById(R.id.recycle_view);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleView.addItemDecoration(new MyItemDecoration(this,10));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initDatas() {
        //init EntityList,async load user and TweetList,but the first of moments list is user's information.
        mEntityList.add(mUser);
        presenter.loadUserInfo();
        presenter.firstLoadTweetList();
    }

    @Override
    public void showUserInfo(UserEntity user) {
        if (user != null) {
            mUser = user;
            mEntityList.set(0, user);
            mRecycleView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void showTweetList(List<TweetEntity> tweets) {
        if (tweets != null && tweets.size() > 0) {
            mEntityList.removeAll(mTweetList);
            mTweetList.clear();
            mTweetList.addAll(tweets);
            mEntityList.addAll(mTweetList);
            mRecycleView.setRefreshComplete();
        }
    }

    @Override
    public void onRefresh(List<TweetEntity> tweets) {
        showTweetList(tweets);
    }

    @Override
    public void onLoadMore(List<TweetEntity> tweets) {
        if (tweets != null && tweets.size() > 0) {
            mTweetList.addAll(tweets);
            mEntityList.addAll(tweets);
        }
        mRecycleView.setLoadMoreComplete();
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }
    }

    public void showImageDetail(TweetEntity.ImageInfo imageInfo) {
        if (imageInfo != null) {
            Intent intent = new Intent(this, ImageDetailActivity.class);
            intent.putExtra(ImageDetailActivity.IMAGE_URL, imageInfo.getId());
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }
}
