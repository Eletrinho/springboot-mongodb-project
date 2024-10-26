package com.eletrinho.springmongo.services;

import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.entities.User;
import com.eletrinho.springmongo.repository.PostRepository;
import com.eletrinho.springmongo.repository.UserRepository;
import com.eletrinho.springmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> user = postRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Post insert(Post post) {
        return postRepository.save(post);
    }

    public void deleteById(String id) {
        findById(id);
        postRepository.deleteById(id);
    }

    public Post put(Post post) {
        Post obj = findById(post.getId());
        obj.setTitle(post.getTitle());
        obj.setBody(post.getBody());
        return postRepository.save(obj);
    }
}
