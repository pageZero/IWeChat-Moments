package com.zjl.iwechatmoments.http.util;

import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.entity.UserEntity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zjl on 18-3-15.
 */
public class TweetEntityFilterTest {
    @Test
    public void filter() throws Exception {
        TweetEntity firstTweet = new TweetEntity();
        firstTweet.setContent("wow");
        UserEntity firstUser = new UserEntity(null,
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w",
                "Joe Portman", "jport");
        firstTweet.setSender(firstUser);

        TweetEntity secondTweet = new TweetEntity();
        secondTweet.setSender(firstUser);

        TweetEntity thirdTweet = new TweetEntity();
        TweetEntity.ImageInfo imageInfo = thirdTweet.new ImageInfo();
        imageInfo.setUrl("http://i.ytimg.com/vi/rGWI7mjmnNk/hqdefault.jpg");
        List<TweetEntity.ImageInfo> imageInfos = new ArrayList<>();
        imageInfos.add(imageInfo);
        thirdTweet.setSender(firstUser);
        thirdTweet.setImages(imageInfos);

        List<TweetEntity> tweets = new ArrayList<>();
        tweets.add(firstTweet);
        tweets.add(secondTweet);
        tweets.add(thirdTweet);

        List<TweetEntity> result = new TweetEntityFilter().filter(tweets);
        assertEquals(2, result.size());

    }

}