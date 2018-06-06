package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.Video;
import com.xingkong.lyn.service.anjian.IVideo;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@RestController
public class VideoController {
    @Resource
    private IVideo videoService;

    @RequestMapping(value = "/web/manage/video/list", method = RequestMethod.GET)
//    @RequiresPermissions("video:view")
    public Object webManageVideoList(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("videos", videoService.getVideoList(pageable));
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/video/add", method = RequestMethod.POST)
//    @RequiresPermissions("video:add")
    public Object webManageVideoAdd(@RequestBody Video video) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != video.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            videoService.addVideo(video);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/video/update", method = RequestMethod.PUT)
//    @RequiresPermissions("video:update")
    public Object webManageVideoUpdate(@RequestBody Video video) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == video.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            videoService.updateVideo(video);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/video/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("video:delete")
    public Object webManageVideoDelete(String id) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        videoService.deleteVideo(ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/video/detail", method = RequestMethod.GET)
//    @RequiresPermissions("video:detail")
    public Object webManageVideoDetail(Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("video", videoService.getVideo(id));
        return ajaxResults;
    }
}