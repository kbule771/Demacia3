package com.web.blog.controller.video;

import com.web.blog.model.dto.video.VideoLikes;
import com.web.blog.model.service.video.VideoPostLikesService;
import com.web.blog.util.BasicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class VideoLikeController {
    @Autowired
    VideoPostLikesService likesService;

    @PostMapping("/vlikes/status")
    @ApiOperation(value = "좋아요 상태조회")
    public Object likesStatus(@RequestBody VideoLikes likes) {
        int res = likesService.likesStatus(likes.getVideoPostNo(), likes.getUserNo());
        System.out.println(res);
        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = res;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/vlikes/")
    @ApiOperation(value = "좋아요")
    public Object likesInsert(@RequestBody VideoLikes likes) {
        final BasicResponse result = new BasicResponse();
        if (likesService.likesInsert(likes) != 0) {
            result.status = true;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.status = false;
            result.data = "Duplicated";
            return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/vlikes/")
    @ApiOperation(value = "좋아요 취소")
    public Object likesDelete(int videoPostNo, int userNo) {
        BasicResponse result = new BasicResponse();
        likesService.likesDelete(videoPostNo, userNo);
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}