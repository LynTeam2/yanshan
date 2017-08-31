package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
public interface IVideo {
    Page<Video> getVideoList(Pageable pageable);
    boolean addVideo(Video video);
    boolean updateVideo(Video video);
    boolean deleteVideo(List<Long> id);
    Video getVideo(Long id);
}
