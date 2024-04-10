package com.xunle.jpa.repo;

import com.xunle.jpa.model.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

/**
 * @author xunle
 * @date 2024/4/7
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LessonRepoTest {

    @Autowired
    private LessonRepo lessonRepo;

    @Test
    public void testInsert() {
        Lesson lesson = new Lesson("English");
        Lesson saved = lessonRepo.save(lesson);
        System.out.println(saved);
        Optional<Lesson> found = lessonRepo.findById(saved.getId());

        assert found.isPresent() && found.get().getName().equals(lesson.getName());
    }

}
