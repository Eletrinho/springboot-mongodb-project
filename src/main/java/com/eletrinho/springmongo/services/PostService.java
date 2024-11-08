package com.eletrinho.springmongo.services;

import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.repository.PostRepository;
import com.eletrinho.springmongo.services.exception.ObjectNotFoundException;
import com.eletrinho.springmongo.services.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findFist25() {
        Pageable first25 = PageRequest.of(0, 25);
        return postRepository.findAll(first25).getContent();
    }

    public Post findById(String id) {
        Optional<Post> user = postRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Post insert(Post post) {
        return postRepository.save(post);
    }

    public void deleteById(String id, String currentUsername) {
        Post obj = findById(id);
        if (currentUsername.equals(obj.getAuthor().getUsername())) {
            postRepository.deleteById(id);
            return;
        }
        throw new UnauthorizedException("Você não é dono dessa postagem");
    }

    public Post put(Post post, String id, String currentUsername) {
        Post obj = findById(id);
        if (currentUsername.equals(obj.getAuthor().getUsername())) {
            obj.setTitle(post.getTitle());
            obj.setBody(post.getBody());
            return postRepository.save(obj);
        }
        throw new UnauthorizedException("Você não é dono dessa postagem");
    }

    public List<Post> findAfter(Instant instant) {
        return postRepository.findByDateAfter(instant);
    }

    public List<Post> findBefore(Instant instant) {
        return postRepository.findByDateBefore(instant);
    }

    public List<Post> findByTitle(String text) {
        return postRepository.findByTitle(text);
    }
}
