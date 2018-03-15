package com.zjl.iwechatmoments.http.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.entity.UserEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjl on 18-3-15.
 */

public class JsonUtils {

    public static UserEntity jsonToUserEntity(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            UserEntity userEntity = new UserEntity();
            userEntity.setProfileImage(jsonObject.optString(UserEntity.Tag.PROFILEIMAGETAG));
            userEntity.setAvatar(jsonObject.optString(UserEntity.Tag.AVATARTAG));
            userEntity.setNick(jsonObject.optString(UserEntity.Tag.NICKTAG));
            userEntity.setUserName(jsonObject.optString(UserEntity.Tag.USERNAMETAG));
            return userEntity;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<TweetEntity> jsonToTweets(String json) {
        return jsonToTweets(json, null);
    }

    public static List<TweetEntity> jsonToTweets(String json, Filter<TweetEntity> filter) {
        JsonArray tweetsJsonArray = new JsonParser().parse(json).getAsJsonArray();
        ArrayList<TweetEntity> tweets = new ArrayList<>();
        Gson gson = new Gson();
        for (JsonElement element : tweetsJsonArray) {
            TweetEntity tweet = gson.fromJson(element, TweetEntity.class);
            tweets.add(tweet);
        }
        if (filter != null) {
            return filter.filter(tweets);
        }
        return tweets;
    }
}
