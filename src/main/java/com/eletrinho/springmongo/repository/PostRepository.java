package com.eletrinho.springmongo.repository;

import com.eletrinho.springmongo.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByDateAfter(Instant date);
    List<Post> findByDateBefore(Instant date);
}
