package com.xingkong.lyn.repository;

import com.xingkong.lyn.model.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lyn on 2017/5/26.
 */
public interface SysRoleRepository extends JpaRepository<SysRole,Long> {
    List<SysRole> findAllByRole(String role);
}
