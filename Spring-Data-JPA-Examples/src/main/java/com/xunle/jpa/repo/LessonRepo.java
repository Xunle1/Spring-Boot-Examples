package com.xunle.jpa.repo;

import com.xunle.jpa.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xunle
 * @date 2024/4/7
 */
public interface LessonRepo extends JpaRepository<Lesson, Long> {

    Lesson findByName(String name);
}
