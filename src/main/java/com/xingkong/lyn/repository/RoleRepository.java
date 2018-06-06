package com.xingkong.lyn.repository;

import com.xingkong.lyn.model.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyn on 2017/6/1.
 */
public interface RoleRepository extends JpaRepository<SysRole, Long> {
}
