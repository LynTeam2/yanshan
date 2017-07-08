package com.xingkong.lyn.controller;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.Message;
import com.xingkong.lyn.service.impl.MessageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Bug Man on 2017-07-05.
 */

@RestController
public class MessageController {
    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Resource
    private MessageService messageService;

    //查看列表
    @RequestMapping(value = "/web/manage/message/list", method = RequestMethod.GET)
    //@RequiresPermissions("message:view")
    //@AdminLog(value = "网站:留言列表")
    public Object webMessagesList(@PageableDefault(value = 15, sort = { "createTime" }, direction = Sort.Direction.DESC)
                                      Pageable pageable){
        AjaxResults ajaxResults = new AjaxResults();
        Page<Message> massages = messageService.getMessageByPageable(pageable);
        ajaxResults.put("messageList", massages);
        return ajaxResults;
    }

    //查看详情
    @RequestMapping(value = "/web/manage/message/detail", method = RequestMethod.GET)
    //@RequiresPermissions("message:detail")
    //@AdminLog(value = "网站:留言详情")
    public Object webMessageDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        Message message = messageService.getMessageById(id);
        ajaxResults.put("information", message);
        return ajaxResults;
    }

    //新增
    @RequestMapping(value = "/web/message/add", method = RequestMethod.POST)
    public Object webManageNewsAdd(Message message){
        AjaxResults ajaxResults = new AjaxResults();
        message.setCreateTime(new Date());
        messageService.addMessage(message);
        return ajaxResults;
    }


    //删除
    @RequestMapping(value = "/web/manage/message/delete", method = RequestMethod.DELETE)
    //@RequiresPermissions("message:delete")
    //@AdminLog(value = "网站:删除留言")
    public Object webManageNewsDelete(String id){
        AjaxResults ajaxResults = new AjaxResults();
        String[] ids = id.split(",");
        Long[] arr = new Long[ids.length];
        for(int i=0;i<arr.length;i++){
            arr[i]=Long.parseLong(ids[i]);
        }
        List<Long> idList= Arrays.asList(arr);
        messageService.deleteList(idList);
        return ajaxResults;
    }

}
