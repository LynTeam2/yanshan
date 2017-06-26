package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.manage.SysLog;
import com.xingkong.lyn.repository.manage.SysLogRepository;
import com.xingkong.lyn.service.ISysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/6/20.
 */
@Service
public class SysLogService implements ISysLog{
    @Resource
    private SysLogRepository sysLogDao;

    @Override
    public boolean addSysLog(SysLog sysLog) {
        sysLogDao.saveAndFlush(sysLog);
        return false;
    }

    @Override
    public Page getSysLogs(String operator, String content, Pageable pageable) {
        return sysLogDao.getSysLogsByOperatorOrContent(operator, content, pageable);
    }
}
