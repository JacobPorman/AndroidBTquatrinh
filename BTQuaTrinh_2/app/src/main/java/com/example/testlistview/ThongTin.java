package com.example.testlistview;

import android.net.Uri;

public class ThongTin {
    private String title;
    private String content;
    private String time;
    private String image;

    public ThongTin(String title, String content, String time, String image) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.image = image;
    }

    public ThongTin(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
