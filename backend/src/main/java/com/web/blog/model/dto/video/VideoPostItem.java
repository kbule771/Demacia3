package com.web.blog.model.dto.video;

import java.time.LocalDateTime;

public class VideoPostItem {
	private int videoPostNo;
	private int userNo;
	private String userNickname;
	private String userImage;
	private String thumbnail;
	private int isPrivate;
	private int view;
	private LocalDateTime postdate;
	private int totalLike;

	public int getVideoPostNo() {
		return videoPostNo;
	}

	public void setVideoPostNo(int videoPostNo) {
		this.videoPostNo = videoPostNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(int isPrivate) {
		this.isPrivate = isPrivate;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public LocalDateTime getPostdate() {
		return postdate;
	}

	public void setPostdate(LocalDateTime postdate) {
		this.postdate = postdate;
	}

	public int getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(int totalLike) {
		this.totalLike = totalLike;
	}

	public VideoPostItem(int videoPostNo, int userNo, String userNickname, String userImage, String thumbnail,
			int isPrivate, int view, LocalDateTime postdate, int totalLike) {
		this.videoPostNo = videoPostNo;
		this.userNo = userNo;
		this.userNickname = userNickname;
		this.userImage = userImage;
		this.thumbnail = thumbnail;
		this.isPrivate = isPrivate;
		this.view = view;
		this.postdate = postdate;
		this.totalLike = totalLike;
	}

	public VideoPostItem() {
	}

	@Override
	public String toString() {
		return "VideoPostItem [isPrivate=" + isPrivate + ", postdate=" + postdate + ", thumbnail=" + thumbnail
				+ ", totalLike=" + totalLike + ", userImage=" + userImage + ", userNickname=" + userNickname
				+ ", userNo=" + userNo + ", videoPostNo=" + videoPostNo + ", view=" + view + "]";
	}

}