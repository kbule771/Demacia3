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
public class Video {
	private int videoPostNo;
	private int userNo;
	private String video;
	private String thumbnail;
	private String data; // JSON
	private int isPrivate;
	private int view;
	private LocalDateTime postdate;

	@Builder
	public Video(int videoPostNo, int userNo, String video, String thumbnail, String data, int isPrivate, int view,
			LocalDateTime postdate) {
		this.videoPostNo = videoPostNo;
		this.userNo = userNo;
		this.video = video;
		this.thumbnail = thumbnail;
		this.data = data;
		this.isPrivate = isPrivate;
		this.view = view;
		this.postdate = postdate;
	}
}