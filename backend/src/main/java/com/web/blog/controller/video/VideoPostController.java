package com.web.blog.controller.video;

import java.util.List;

import com.web.blog.model.dto.video.Video;
import com.web.blog.model.dto.video.VideoLikes;
import com.web.blog.model.dto.video.VideoPostItem;
import com.web.blog.model.service.video.VideoPostLikesService;
import com.web.blog.model.service.video.VideoPostService;
import com.web.blog.util.BasicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class VideoPostController {

    @Autowired
    VideoPostService videoPostService;
    @Autowired
    VideoPostLikesService likesService;

    @PostMapping("/video/")
    @ApiOperation(value = "영상 등록")
    public Object insert(@RequestBody Video video) {
        System.out.println(video);
        BasicResponse result = new BasicResponse();
        try {
            result.status = videoPostService.insert(video) != 0;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Fail to Insert video post");
            result.data = "fail";
            result.object = e;
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/video/search")
    @ApiOperation(value = "영상 검색")
    public Object postSearch(int limit, int offset, String option, String orderBy) {
        BasicResponse result = new BasicResponse();
        try {
            List<VideoPostItem> list = videoPostService.search(limit, offset, option, orderBy);
            System.out.println("list count : " + list.size());
            result.status = true;
            result.data = "success";
            result.object = list;
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Fail to get video post list : " + e);
            result.status = false;
            result.data = "fail";
            result.object = e;
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/video/detail")
    @ApiOperation(value = "영상 상세조회")
    public Object postDetail(int videoPostNo) {
        System.out.println(videoPostNo);
        BasicResponse result = new BasicResponse();
        try {
            result.object = videoPostService.detail(videoPostNo);
            result.status = true;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Fail to get video post detail");
            result.status = false;
            result.data = "fail";
            result.object = e;
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/video/")
    @ApiOperation(value = "영상 수정")
    public Object postUpdate(@RequestBody Video video) {
        System.out.println(video);
        BasicResponse result = new BasicResponse();
        try {
            videoPostService.update(video);
            result.status = true;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Fail to video post update");
            result.status = false;
            result.data = "fail";
            result.object = e;
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/video/")
    @ApiOperation(value = "영상 삭제")
    public Object postDelete(int videoPostNo) {
        BasicResponse result = new BasicResponse();
        try {
            videoPostService.delete(videoPostNo);
            result.status = true;
            result.data = "success";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Fail to video post delete");
            result.status = false;
            result.data = "fail";
            result.object = e;
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}