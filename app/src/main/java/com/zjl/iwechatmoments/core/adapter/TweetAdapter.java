package com.zjl.iwechatmoments.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.zjl.iwechatmoments.R;
import com.zjl.iwechatmoments.http.entity.TweetEntity;

import java.util.List;


/**
 * Created by zjl on 18-3-15.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder> {
    private List<TweetEntity> mTweets;
    private Context mContext;

    public TweetAdapter(List<TweetEntity> tweets, Context context) {
        mTweets = tweets;
        mContext = context;
    }
    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TweetViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_tweet, parent, false));
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        holder.bindView(mTweets.get(position));
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public class TweetViewHolder extends RecyclerView.ViewHolder {
        private ImageView mAvatarIv;
        private TextView mNickTv;
        private TextView mContentTv;
        private NineGridImageView mImageGridIv;
        private LinearLayout mCommentLayout;

        private NineGridImageViewAdapter<TweetEntity.ImageInfo> gridViewAdapter = new NineGridImageViewAdapter<TweetEntity.ImageInfo>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, TweetEntity.ImageInfo imageInfo) {
                Glide.with(mContext)
                        .load(imageInfo.getUrl())
                        .into(mAvatarIv);
            }
        };

        public TweetViewHolder(View itemView) {
            super(itemView);
            mAvatarIv = itemView.findViewById(R.id.avatar_iv);
            mNickTv = itemView.findViewById(R.id.nick_tv);
            mContentTv = itemView.findViewById(R.id.content_tv);
            mImageGridIv = itemView.findViewById(R.id.nine_grid_iv);
            mImageGridIv.setAdapter(gridViewAdapter);
            mCommentLayout = itemView.findViewById(R.id.comments_layout);
        }

        public void bindView(TweetEntity tweet) {
            // TODO: 18-3-16 load icon
//            Glide.with(mContext)
//                    .load(tweet.getSender().getAvatar())
//                    .into(mAvatarIv);
            mNickTv.setText(tweet.getSender().getNick());
            if (tweet.getContent() != null) {
                mContentTv.setVisibility(View.VISIBLE);
                mContentTv.setText(tweet.getContent());
            } else {
                mContentTv.setVisibility(View.GONE);
            }
            // TODO: 18-3-16 load images
//            mImageGridIv.setImagesData(tweet.getImages());
            mImageGridIv.setVisibility(View.GONE);
            mCommentLayout.removeAllViews();
            if (tweet.getComments() != null && tweet.getComments().size() > 0) {
                Log.e(TweetAdapter.class.getSimpleName(), "content:" + tweet.getContent() + "...comment.size:" + tweet.getComments().size());
                for (TweetEntity.Comment item: tweet.getComments()) {
                    View commentView = LayoutInflater.from(mContext).inflate(R.layout.item_comment, mCommentLayout, false);
                    TextView commentSender = commentView.findViewById(R.id.sender_tv);
                    TextView commentContent = commentView.findViewById(R.id.comment_tv);
                    mCommentLayout.addView(commentView);
                    if (commentSender != null) {
                        commentSender.setText(item.getSender().getNick());
                    }
                    if (commentContent != null) {
                        commentContent.setText(item.getContent());
                    }
                }
            }
        }
    }
}
