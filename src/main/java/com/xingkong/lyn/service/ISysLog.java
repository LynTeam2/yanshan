package com.xingkong.lyn.service;

import com.xingkong.lyn.model.manage.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lyn on 2017/6/20.
 */
public interface ISysLog {
    boolean addSysLog(SysLog sysLog);
    Page<SysLog> getSysLogs(String operator, String content, Pageable pageable);
}
