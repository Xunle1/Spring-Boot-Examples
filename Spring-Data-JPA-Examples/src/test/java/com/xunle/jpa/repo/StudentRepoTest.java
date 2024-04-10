package com.xunle.jpa.repo;

import com.xunle.jpa.model.Lesson;
import com.xunle.jpa.model.Student;
import com.xunle.jpa.model.StudentLesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xunle
 * @date 2024/4/7
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private LessonRepo lessonRepo;

    @Test
    public void testInsert() {
        Student student = new Student("foo");
        Student saved = studentRepo.save(student);
        System.out.println(saved);
        Optional<Student> found = studentRepo.findById(saved.getId());

        assert found.isPresent() && found.get().getName().equals(student.getName());
    }


    @Test
    public void testDelete() {
        Student student = studentRepo.findByName("foo");
        studentRepo.deleteById(student.getId());

        assert !studentRepo.existsById(student.getId());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testInsertWithLessons() {
        Student student = new Student("bar");
        Lesson lesson = lessonRepo.findByName("Math");

        List<StudentLesson> lessons = new ArrayList<>();
        StudentLesson studentLesson = new StudentLesson();
        studentLesson.setDuration(3);
        studentLesson.setStudent(student);
        studentLesson.setLesson(lesson);
        lessons.add(studentLesson);

        student.setLessons(lessons);
        studentRepo.save(student);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testUpdateWithLessons() {
        Student student = studentRepo.findByName("bar");
        Lesson lesson1 = lessonRepo.findByName("Computer Science");
        Lesson lesson2 = lessonRepo.findByName("English");
        System.out.println("========== before updating ==========");
//        System.out.println(student);
//        studentLessonRepo.findAll().forEach(System.out::println);

        List<StudentLesson> lessons = new ArrayList<>();
        StudentLesson studentLesson1 = new StudentLesson();
        studentLesson1.setDuration(2);
        studentLesson1.setStudent(student);
        studentLesson1.setLesson(lesson1);
        lessons.add(studentLesson1);

        StudentLesson studentLesson2 = new StudentLesson();
        studentLesson2.setDuration(2);
        studentLesson2.setStudent(student);
        studentLesson2.setLesson(lesson2);
        lessons.add(studentLesson2);

        student.setLessons(lessons);
        studentRepo.save(student);
        System.out.println("========== after updating once ==========");
//        System.out.println(studentRepo.findByName("bar"));
//        studentLessonRepo.findAll().forEach(System.out::println);

//        student.setLessons(new ArrayList<>());
//        studentRepo.save(student);
//        System.out.println("========== after updating twice ==========");
//        System.out.println(studentRepo.findByName("bar"));
//        studentLessonRepo.findAll().forEach(System.out::println);

    }
}
