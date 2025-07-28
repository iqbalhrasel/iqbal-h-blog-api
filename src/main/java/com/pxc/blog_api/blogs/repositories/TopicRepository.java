package com.pxc.blog_api.blogs.repositories;

import com.pxc.blog_api.blogs.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Short> {
    Optional<Topic> findByName(String name);
    List<Topic> findAllByOrderByIdAsc();


}
