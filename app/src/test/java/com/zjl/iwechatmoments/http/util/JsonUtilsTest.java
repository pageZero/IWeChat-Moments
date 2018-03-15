package com.zjl.iwechatmoments.http.util;

import com.zjl.iwechatmoments.http.entity.TweetEntity;
import com.zjl.iwechatmoments.http.entity.UserEntity;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by zjl on 18-3-15.
 */
public class JsonUtilsTest {
    @Test
    public void jsonToUserEntity() throws Exception {
        String userJson = "{\n" +
                "  \"profile-image\": \"http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png\",\n" +
                "  \"avatar\": \"http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png\",\n" +
                "  \"nick\": \"John Smith\",\n" +
                "  \"username\": \"jsmith\"\n" +
                "}";
        UserEntity user = JsonUtils.jsonToUserEntity(userJson);
        assertTrue(user != null);
        assertTrue(user.getProfileImage() != null);
        assertTrue(user.getAvatar() != null);
        assertEquals("John Smith",user.getNick());
        assertEquals("username", user.getUserName());
    }

    @Test
    public void jsonToTweets() throws Exception {
        //only one tweet
        String tweetsJson = "[{\n" +
                "\t\t\"content\": \"沙发！\",\n" +
                "\t\t\"images\": [{\n" +
                "\t\t\t\t\"url\": \"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRDy7HZaHxn15wWj6pXE4uMKAqHTC_uBgBlIzeeQSj2QaGgUzUmHg\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"url\": \"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTlJRALAf-76JPOLohBKzBg8Ab4Q5pWeQhF5igSfBflE_UYbqu7\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"url\": \"http://i.ytimg.com/vi/rGWI7mjmnNk/hqdefault.jpg\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"sender\": {\n" +
                "\t\t\t\"username\": \"jport\",\n" +
                "\t\t\t\"nick\": \"Joe Portman\",\n" +
                "\t\t\t\"avatar\": \"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w\"\n" +
                "\t\t},\n" +
                "\t\t\"comments\": [{\n" +
                "\t\t\t\t\"content\": \"Good.\",\n" +
                "\t\t\t\t\"sender\": {\n" +
                "\t\t\t\t\t\"username\": \"outman\",\n" +
                "\t\t\t\t\t\"nick\": \"Super hero\",\n" +
                "\t\t\t\t\t\"avatar\": \"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"content\": \"Like it too\",\n" +
                "\t\t\t\t\"sender\": {\n" +
                "\t\t\t\t\t\"username\": \"inman\",\n" +
                "\t\t\t\t\t\"nick\": \"Doggy Over\",\n" +
                "\t\t\t\t\t\"avatar\": \"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}]";
        List<TweetEntity> tweets = JsonUtils.jsonToTweets(tweetsJson);
        assertEquals(1, tweets.size());
        TweetEntity tweet = tweets.get(0);
        assertTrue(tweet.getContent() != null);
        assertTrue(tweet.getImages() != null);
        assertEquals(3, tweet.getImages().size());
        assertTrue(tweet.getSender() != null);
        assertTrue(tweet.getComments() != null);
        assertEquals(2, tweet.getComments().size());
    }

    @Test
    public void jsonToTweets1() throws Exception {
        //same to jsonToTweets()
    }
}