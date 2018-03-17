package com.zjl.iwechatmoments.http.entity;

import java.util.List;

/**
 * Created by zjl on 18-3-14.
 */

public class TweetEntity extends Entity{
    private String content;
    private List<ImageInfo> images;
    private UserEntity sender ;
    private List<Comment> comments;

    public String getContent() {
        return content;
    }

    public List<ImageInfo> getImages() {
        return images;
    }

    public UserEntity getSender() {
        return sender;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImages(List<ImageInfo> images) {
        this.images = images;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {

        return "TweetEntity: " +
                "content=" + content +
                ",images=" + images +
                ",sender=" + sender +
                ",comments=" + comments +
                "..." + super.toString();
    }

    public class ImageInfo {
        private String url;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "ImageInfo: " +
                    "url=" + url +
                    "..." + super.toString();
        }
    }

    public class Comment {
        private String content;
        private UserEntity sender;

        public void setContent(String content) {
            this.content = content;
        }

        public void setSender(UserEntity sender) {
            this.sender = sender;
        }

        public String getContent() {
            return content;
        }

        public UserEntity getSender() {
            return sender;
        }

        @Override
        public String toString() {

            return "Comment: " +
                    "content" + content +
                    ",sender:" + sender.toString() +
                    "..." + super.toString();
        }
    }
}
