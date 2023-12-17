package com.example.multidatabase.topic.repository;

import com.example.multidatabase.topic.entity.Topic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

@SqlGroup({
    @Sql(value = "/sql/insertTopic.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
})
@SpringBootTest
class TopicRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;

    @AfterEach
    void tearDown() {
        topicRepository.deleteAll();
    }


    @DisplayName("Topic 레포지토리와 연결된 DB에서 ID로 조회가 가능하다.")
    @Test
    void testFindById() {
        // given
        // when
        Topic topic = topicRepository.findById(1L).orElseThrow();

        // then
        Assertions.assertThat(topic).isNotNull();
    }
}