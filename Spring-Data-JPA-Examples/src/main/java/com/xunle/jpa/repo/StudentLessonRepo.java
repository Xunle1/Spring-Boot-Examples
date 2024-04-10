package com.xunle.jpa.repo;

import com.xunle.jpa.model.Lesson;
import com.xunle.jpa.model.Student;
import com.xunle.jpa.model.StudentLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xunle
 * @date 2024/4/7
 */
public interface StudentLessonRepo extends JpaRepository<StudentLesson, Long> {

    List<StudentLesson> findAllByStudent(Student student);

    List<StudentLesson> findAllByLesson(Lesson lesson);

    List<StudentLesson> findAllByLessonAndStudent(Lesson lesson, Student student);
}
