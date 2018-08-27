package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    Page<User> findByUserNameLike(String userName, Pageable pageable);
    Long countByUserName(String userName);
}
