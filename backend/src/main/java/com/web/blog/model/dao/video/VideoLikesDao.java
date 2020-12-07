package com.web.blog.model.dao.video;

import com.web.blog.model.dto.video.VideoLikes;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VideoLikesDao {

    // like 개수 조회
    public int likesCount(int videoPostNo);

    // like 버튼 클릭(비활성화 -> 활성화)
    public int likesInsert(VideoLikes likes);

    // like 버튼 클릭(활성화 -> 비활성화)
    public int likesDelete(int videoPostNo, int userNo);

    // like 상태 조회
    public int likesStatus(int videoPostNo, int userNo);

}