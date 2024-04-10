package com.xunle.jpa.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author xunle
 * @date 2024/4/7
 */

@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentLesson> lessons;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, List<StudentLesson> lessons) {
        this.name = name;
        this.lessons = lessons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentLesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<StudentLesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lessons=" + lessons +
                '}';
    }
}
