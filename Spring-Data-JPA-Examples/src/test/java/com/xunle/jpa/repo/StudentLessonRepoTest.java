package com.xunle.jpa.repo;

import com.xunle.jpa.model.Lesson;
import com.xunle.jpa.model.Student;
import com.xunle.jpa.model.StudentLesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xunle
 * @date 2024/4/7
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentLessonRepoTest {

    @Autowired
    private StudentLessonRepo studentLessonRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private LessonRepo lessonRepo;

    @Test
    public void testInsert() {
        Lesson lesson = lessonRepo.findByName("English");
        Student student = studentRepo.findByName("foo");

        StudentLesson studentLesson = new StudentLesson(2, lesson, student);
        studentLessonRepo.save(studentLesson);
        studentLessonRepo.findAll().forEach(System.out::println);
        studentRepo.findAll().forEach(System.out::println);
        System.out.println(lessonRepo.findByName("English"));
    }

    @Test
    public void testCascadeInsert() {
        Student student = studentRepo.findByName("foo");
        List<StudentLesson> list = new ArrayList<>();
        List<Lesson> lessons = lessonRepo.findAll();
        lessons.forEach(lesson -> {
            list.add(new StudentLesson(1, lesson, student));
        });

        student.setLessons(list);
        Student saved = studentRepo.save(student);
        System.out.println(saved);
        studentLessonRepo.findAll().forEach(System.out::println);
    }

    private List<Student> initStudentList;
    private List<Lesson> initLessonList;

    private void init() {
        // create lessons
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("test-lesson-1"));
        lessons.add(new Lesson("test-lesson-2"));
        lessons.add(new Lesson("test-lesson-3"));
        initLessonList = lessonRepo.saveAll(lessons);

        // create students
        List<Student> students = new ArrayList<>();
        students.add(new Student("test-stu-1"));
        students.add(new Student("test-stu-2"));
        initStudentList = studentRepo.saveAll(students);

        // cascade insert
        List<StudentLesson> list = new ArrayList<>();
        list.add(new StudentLesson(2, initLessonList.get(0), initStudentList.get(0)));
        list.add(new StudentLesson(1, initLessonList.get(1), initStudentList.get(0)));
        list.add(new StudentLesson(4, initLessonList.get(1), initStudentList.get(1)));
        list.add(new StudentLesson(2, initLessonList.get(2), initStudentList.get(1)));

        studentLessonRepo.saveAll(list);
    }

    private void destroy() {
        studentRepo.deleteAll(initStudentList);
        lessonRepo.deleteAll(initLessonList);
    }

    @Test
    public void testCascadeDelete() {
        // initialize students and lessons
        init();
        System.out.println("========== after init ==========");
        System.out.println("========== students ==========");
        studentRepo.findAll().forEach(System.out::println);
        System.out.println("========== lessons ==========");
        lessonRepo.findAll().forEach(System.out::println);
        System.out.println("========== student_lesson ==========");
        studentLessonRepo.findAll().forEach(System.out::println);
        System.out.println();

        // cascade delete
        studentRepo.deleteAll(initStudentList);
        System.out.println("========== after deleting the students ==========");
        System.out.println("========== students ==========");
        studentRepo.findAll().forEach(System.out::println);
        System.out.println("========== lessons ==========");
        lessonRepo.findAll().forEach(System.out::println);
        System.out.println("========== student_lesson ==========");
        studentLessonRepo.findAll().forEach(System.out::println);
        System.out.println();

        lessonRepo.deleteAll(initLessonList);
        System.out.println("========== after deleting the lessons ==========");
        System.out.println("========== students ==========");
        studentRepo.findAll().forEach(System.out::println);
        System.out.println("========== lessons ==========");
        lessonRepo.findAll().forEach(System.out::println);
        System.out.println("========== student_lesson ==========");
        studentLessonRepo.findAll().forEach(System.out::println);
    }

    @Test
    public void testQuery() {
        init();
        Student student1 = initStudentList.get(0);
        System.out.println("========== query by student " + student1 + " ==========");
        studentLessonRepo.findAllByStudent(student1).forEach(System.out::println);

        Student student2 = initStudentList.get(1);
        System.out.println("========== query by student " + student2 + " ==========");
        studentLessonRepo.findAllByStudent(student2).forEach(System.out::println);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        lessonRepo.findAllById(ids).forEach(System.out::println);

        destroy();
    }
}
