package com.web.blog.model.dto.video;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VideoPostDetail {
	private int videoPostNo;
	private int userNo;
	private String userNickname;
	private String userImage;
	private String userRank;
	private String gameRank;
	private String video;
	private String data; // JSON
	private int isPrivate;
	private int view;
	private LocalDateTime postdate;
	private int totalLike;

	@Builder
	public VideoPostDetail(int videoPostNo, int userNo, String userNickname, String userImage, String userRank,
			String gameRank, String video, String data, int isPrivate, int view, LocalDateTime postdate,
			int totalLike) {
		this.videoPostNo = videoPostNo;
		this.userNo = userNo;
		this.userNickname = userNickname;
		this.userImage = userImage;
		this.userRank = userRank;
		this.gameRank = gameRank;
		this.video = video;
		this.data = data;
		this.isPrivate = isPrivate;
		this.view = view;
		this.postdate = postdate;
		this.totalLike = totalLike;
	}
}