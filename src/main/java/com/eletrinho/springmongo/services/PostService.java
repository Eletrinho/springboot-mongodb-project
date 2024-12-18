package com.eletrinho.springmongo.services;

import com.eletrinho.springmongo.config.util.JwtUtil;
import com.eletrinho.springmongo.dto.AuthorDTO;
import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.entities.User;
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

    @Autowired
    private UserService userService;

    public List<Post> findFist25() {
        Pageable first25 = PageRequest.of(0, 25);
        return postRepository.findAll(first25).getContent();
    }

    public Post findById(String id) {
        Optional<Post> user = postRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Post insert(Post post, String token) {
        String username = JwtUtil.extractUsername(token.substring(7));
        post.setAuthor(new AuthorDTO(userService.findByUsername(username)));
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
            obj.setBody(post.getBody());
            return postRepository.save(obj);
        }
        throw new UnauthorizedException("Você não é dono dessa postagem");
    }

    public List<Post> findByAuthor(String username){
        return postRepository.findByAuthor(username);
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

    public void savePost(Post post) {
        postRepository.save(post);
    }
}
