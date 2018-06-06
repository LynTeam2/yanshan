package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.Video;
import com.xingkong.lyn.repository.anjian.VideoRepository;
import com.xingkong.lyn.service.anjian.IVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoService implements IVideo {
    @Resource
    private VideoRepository videoDao;

    @Override
    public Page<Video> getVideoList(Pageable pageable) {
        return null;
    }

    @Override
    public boolean addVideo(Video video) {
        return false;
    }

    @Override
    public boolean updateVideo(Video video) {
        return false;
    }

    @Override
    public boolean deleteVideo(List<Long> id) {
        return false;
    }

    @Override
    public Video getVideo(Long id) {
        return null;
    }
}
