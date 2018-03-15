package com.zjl.iwechatmoments.http.util;

import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjl on 18-3-15.
 * filter the tweet which does not contain a content and images
 */

public class TweetEntityFilter implements Filter<TweetEntity> {
    @Override
    public List<TweetEntity> filter(List<TweetEntity> items) {
        if (items == null || items.size() == 0)
            return null;
        List<TweetEntity> tweets = new ArrayList<>();
        for (TweetEntity item : items) {
            if (item.getSender() != null) {
                if (item.getContent() != null || (item.getImages() != null && item.getImages().size() != 0)) {
                    tweets.add(item);
                }
            }
        }
        return tweets;
    }
}
