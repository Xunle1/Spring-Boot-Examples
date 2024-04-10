package com.xunle.jpa.repo;

import com.xunle.jpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xunle
 * @date 2024/4/7
 */
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Student findByName(String name);
}
