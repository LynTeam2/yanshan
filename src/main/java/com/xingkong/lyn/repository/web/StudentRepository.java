package com.xingkong.lyn.repository.web;

import com.xingkong.lyn.model.web.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyn on 2017/8/30.
 */
public interface StudentRepository extends JpaRepository<Student, Long>{
}
