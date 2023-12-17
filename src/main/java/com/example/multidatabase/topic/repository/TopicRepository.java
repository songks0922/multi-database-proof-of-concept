package com.example.multidatabase.topic.repository;

import com.example.multidatabase.topic.entity.Topic;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findById(Long id);
}
