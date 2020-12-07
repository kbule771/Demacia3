package com.web.blog.model.service.video;

import java.sql.SQLException;

import com.web.blog.model.dao.video.VideoLikesDao;
import com.web.blog.model.dto.video.VideoLikes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoPostLikesService {

    @Autowired
    private VideoLikesDao dao;

    public int likesCount(int videoPostNo) throws SQLException {
        return dao.likesCount(videoPostNo);
    }

    public int likesInsert(VideoLikes likes) {
        if (dao.likesStatus(likes.getVideoPostNo(), likes.getUserNo()) == 0) {
            return dao.likesInsert(likes);
        } else {
            return 0;
        }
    }

    public int likesDelete(int videoPostNo, int userNo) {
        return dao.likesDelete(videoPostNo, userNo);
    }

    public int likesStatus(int videoPostNo, int userNo) {
        return dao.likesStatus(videoPostNo, userNo);
    }
}