package com.eletrinho.springmongo.services;

import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class PostService implements Serializable {

    @Autowired
    private PostRepository postRepository;


    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
