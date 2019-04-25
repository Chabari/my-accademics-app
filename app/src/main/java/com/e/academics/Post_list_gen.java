package com.e.academics;

import java.util.Date;

public class Post_list_gen extends PostId {
    String fullname,post,regno,image_url;
    Date timeStamp;

    public Post_list_gen(){

    }

    public Post_list_gen(String fullname, String post,String image_url, String regno, Date timeStamp) {
        this.fullname = fullname;
        this.post = post;
        this.regno = regno;
        this.image_url = image_url;
        this.timeStamp = timeStamp;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
