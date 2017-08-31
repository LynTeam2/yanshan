package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Video;
import com.xingkong.lyn.repository.web.VideoRepository;
import com.xingkong.lyn.service.IVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@Service
public class VideoService implements IVideo{
    @Resource
    private VideoRepository videoDao;

    @Override
    public Page<Video> getVideoList(Pageable pageable) {
        return videoDao.findAll(pageable);
    }

    @Override
    public boolean addVideo(Video video) {
        if (null != video.getId()) {
            return false;
        }
        videoDao.save(video);
        return true;
    }

    @Override
    public boolean updateVideo(Video video) {
        if (null == video.getId()) {
            return false;
        }
        videoDao.save(video);
        return true;
    }

    @Override
    public boolean deleteVideo(List<Long> id) {
        videoDao.deleteInBatch(videoDao.findAll(id));
        return true;
    }

    @Override
    public Video getVideo(Long id) {
        return videoDao.findOne(id);
    }
}
