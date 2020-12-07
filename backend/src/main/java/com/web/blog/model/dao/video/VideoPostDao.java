package com.web.blog.model.dao.video;

import java.util.List;

import com.web.blog.model.dto.video.Video;
import com.web.blog.model.dto.video.VideoPostDetail;
import com.web.blog.model.dto.video.VideoPostItem;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VideoPostDao {

     // 게시물 등록
     public int insert(Video video);

     // 통합 검색
     public List<VideoPostItem> search(int limit, int offset, String option, String orderBy);

     // 상세 조회
     public VideoPostDetail detail(int videoPostNo);

     // 게시물 삭제
     public int delete(int videoPostNo);

     // 게시물 수정
     public int update(Video video);

     // 조회수
     public int viewCount(int videoPostNo, int view);

}