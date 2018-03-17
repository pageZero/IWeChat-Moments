package com.zjl.iwechatmoments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by zjl on 2018/3/17.
 * show big image
 */

public class ImageDetailActivity extends AppCompatActivity{

    public static final String IMAGE_URL = "imagedetail_activity_image_extra";
    private ImageView mImageIv;
    private int mImageId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_activity);
        mImageId = getIntent().getIntExtra(IMAGE_URL, 0);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Glide.with(this)
                .load(mImageId)
                .into(mImageIv);
    }

    private void initViews() {
        mImageIv = (ImageView) findViewById(R.id.image_detail_iv);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                finish();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }
}
