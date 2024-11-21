package com.eletrinho.springmongo.repository;

import com.eletrinho.springmongo.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{'title': { $regex: ?0, $options: 'i' } }")
    List<Post> findByTitle(String text);

    List<Post> findByDateAfter(Instant date);

    List<Post> findByDateBefore(Instant date);

    @Query("{'author.username': { $regex: ?0, $options: 'i' } }")
    List<Post> findByAuthor(String username);
}
