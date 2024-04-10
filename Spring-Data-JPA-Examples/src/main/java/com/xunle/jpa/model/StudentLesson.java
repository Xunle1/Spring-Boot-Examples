package com.xunle.jpa.model;

import jakarta.persistence.*;

/**
 * @author xunle
 * @date 2024/4/7
 */

@Entity(name = "student_lesson")
public class StudentLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Lesson lesson;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Student student;

    public StudentLesson() {
    }

    public StudentLesson(Integer duration, Lesson lesson, Student student) {
        this.duration = duration;
        this.lesson = lesson;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentLesson{" +
                "id=" + id +
                ", duration=" + duration +
                ", lesson=" + lesson.getId() + ": " + lesson.getName() +
                ", student=" + student.getId() + ": " + student.getName() +
                '}';
    }
}
