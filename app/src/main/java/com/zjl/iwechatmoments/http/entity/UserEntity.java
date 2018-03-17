package com.zjl.iwechatmoments.http.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zjl on 18-3-14.
 */

public class UserEntity extends Entity{
    private String profileImage;
    private String avatar;
    private String nick;
    private String userName;

    public UserEntity() {}

    public UserEntity(String profileImage, String avatar, String nick, String userName) {
        this.profileImage = profileImage;
        this.avatar = avatar;
        this.nick = nick;
        this.userName = userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNick() {
        return nick;
    }

    public String getUserName() {
        return userName;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserEntity:"+
                "profileImage=" +  profileImage +
                ", avatar=" + avatar +
                ", nick=" + nick +
                ", userName=" + userName + super.toString();
    }

    public static class Tag {
        public static final String PROFILEIMAGETAG = "profile-image";
        public static final String AVATARTAG = "avatar";
        public static final String NICKTAG = "nick";
        public static final String USERNAMETAG = "username";
    }
}
