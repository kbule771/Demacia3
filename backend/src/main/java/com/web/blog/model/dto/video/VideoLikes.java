package com.web.blog.model.dto.video;

public class VideoLikes {
    private int vpostlikeNo;
    private int userNo;
    private int videoPostNo;

    public VideoLikes(int vpostlikeNo, int userNo, int videoPostNo) {
        this.vpostlikeNo = vpostlikeNo;
        this.userNo = userNo;
        this.videoPostNo = videoPostNo;
    }

    public int getVpostlikeNo() {
        return vpostlikeNo;
    }

    public void setVpostlikeNo(int vpostlikeNo) {
        this.vpostlikeNo = vpostlikeNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getVideoPostNo() {
        return videoPostNo;
    }

    public void setVideoPostNo(int videoPostNo) {
        this.videoPostNo = videoPostNo;
    }

    @Override
    public String toString() {
        return "VideoLikes [userNo=" + userNo + ", videoPostNo=" + videoPostNo + ", vpostlikeNo=" + vpostlikeNo + "]";
    }

    public VideoLikes() {
    }

}