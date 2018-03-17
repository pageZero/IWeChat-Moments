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
import com.zjl.iwechatmoments.MomentsActivity;
import com.zjl.iwechatmoments.R;
import com.zjl.iwechatmoments.core.util.ImageConstructor;
import com.zjl.iwechatmoments.http.entity.Entity;
import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.entity.UserEntity;

import java.util.List;


/**
 * Created by zjl on 18-3-15.
 */

public class EntityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = EntityAdapter.class.getSimpleName();
    private List<Entity> mEntitys;
    private Context mContext;

    private enum ItemViewType {
        ITEM_USER,
        ITEM_TWEET
    }

    public EntityAdapter(List<Entity> entitys, Context context) {
        mEntitys = entitys;
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemViewType.ITEM_USER.ordinal()) {
            return new UserViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_user,parent, false));
        } else if (viewType == ItemViewType.ITEM_TWEET.ordinal()) {
            return new TweetViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_tweet, parent, false));
        } return null;


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ItemViewType.ITEM_USER.ordinal();
        } else {
            return ItemViewType.ITEM_TWEET.ordinal();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).bindView((UserEntity) mEntitys.get(position));
        } else {
            ((TweetViewHolder) holder).bindView((TweetEntity) mEntitys.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mEntitys.size();
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
                Glide.with(context)
                        .load(imageInfo.getId())
                        .into(imageView);
            }

            @Override
            protected ImageView generateImageView(Context context) {
                return super.generateImageView(context);
            }

            @Override
            protected void onItemImageClick(Context context, int index, List<TweetEntity.ImageInfo> imageImage) {
                ((MomentsActivity) mContext).showImageDetail(imageImage.get(index));
            }
        };

        public TweetViewHolder(View itemView) {
            super(itemView);
            mAvatarIv = itemView.findViewById(R.id.avatar_iv);
            mNickTv = itemView.findViewById(R.id.nick_tv);
            mContentTv = itemView.findViewById(R.id.content_tv);
            mImageGridIv = itemView.findViewById(R.id.nine_grid_iv);
            mImageGridIv.setAdapter(gridViewAdapter);
            mImageGridIv.setShowStyle(NineGridImageView.STYLE_GRID);
            mCommentLayout = itemView.findViewById(R.id.comments_layout);
        }

        public void bindView(TweetEntity tweet) {
            Glide.with(mContext)
                    .load(ImageConstructor.mappingImageUrl(0))
                    .into(mAvatarIv);
            if (tweet.getSender() != null)
                mNickTv.setText(tweet.getSender().getNick());
            if (tweet.getContent() != null) {
                mContentTv.setVisibility(View.VISIBLE);
                mContentTv.setText(tweet.getContent());
            } else {
                mContentTv.setVisibility(View.GONE);
            }
            // construct image url
            buildImages(tweet.getImages());
            mImageGridIv.setImagesData(tweet.getImages());
            mCommentLayout.removeAllViews();
            if (tweet.getComments() != null && tweet.getComments().size() > 0) {
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

        private void buildImages(List<TweetEntity.ImageInfo> images) {
            if (images != null && images.size() > 0) {
                for(int i = 0; i < images.size(); i++) {
                    images.get(i).setId(ImageConstructor.mappingImageUrl(i));
                }
            }
        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView mProfileImageIv;
        private ImageView mAvatarIv;
        private TextView mNickTv;

        public UserViewHolder(View itemView) {
            super(itemView);
            mProfileImageIv = itemView.findViewById(R.id.profile_image_iv);
            mAvatarIv = itemView.findViewById(R.id.user_avatar_iv);
            mNickTv = itemView.findViewById(R.id.nick_tv);
        }

        public void bindView(UserEntity user) {
            //image constructor
            Glide.with(mContext)
                    .load(ImageConstructor.mappingImageUrl(9))
                    .into(mProfileImageIv);
            Glide.with(mContext)
                    .load(ImageConstructor.mappingImageUrl(8))
                    .into(mAvatarIv);
            mNickTv.setText(user.getNick());
        }
    }
}
