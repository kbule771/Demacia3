package com.web.blog.model.service.video;

import java.util.List;

import com.web.blog.model.dao.user.MileageDao;
import com.web.blog.model.dao.video.VideoPostDao;
import com.web.blog.model.dto.video.Video;
import com.web.blog.model.dto.video.VideoPostDetail;
import com.web.blog.model.dto.video.VideoPostItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoPostService {

    @Autowired
    VideoPostDao dao;
    @Autowired
    MileageDao mileageDao;

    // 게시물 등록
    public int insert(Video video) {
        // 작성시 마일리지 -50 차감
        mileageDao.postMileage(video.getUserNo(), 50);
        return dao.insert(video);
    }

    // 통합 검색
    public List<VideoPostItem> search(int limit, int offset, String option, String orderBy) {
        return dao.search(limit, offset, option, orderBy);
    }

    // 상세 조회
    public VideoPostDetail detail(int videoPostNo) {
        VideoPostDetail video = dao.detail(videoPostNo);
        // 조회수 + 1
        dao.viewCount(videoPostNo, video.getView());
        return video;
    }

    // 게시물 삭제
    public int delete(int videoPostNo) {
        return dao.delete(videoPostNo);
    }

    // 게시물 수정
    public int update(Video video) {
        return dao.update(video);
    }
}