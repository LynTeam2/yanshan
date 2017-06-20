package com.xingkong.lyn.repository.manage;

import com.xingkong.lyn.model.manage.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyn on 2017/6/20.
 */
public interface SysLogRepository extends JpaRepository<SysLog, Long> {
    Page<SysLog> getSysLogsByOperatorOrContent(String operator, String content, Pageable pageable);
}
