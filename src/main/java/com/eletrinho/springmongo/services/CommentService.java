package com.eletrinho.springmongo.services;

import com.eletrinho.springmongo.config.util.JwtUtil;
import com.eletrinho.springmongo.dto.AuthorDTO;
import com.eletrinho.springmongo.entities.CommentDTO;
import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public CommentDTO addComment(String postId, CommentDTO comment, String token) {
        AuthorDTO author = new AuthorDTO(userService.findByUsername(JwtUtil.extractUsername(token.substring(7))));
        comment.setAuthor(author);
        Post post = postService.findById(postId);
        post.addComment(comment);
        postService.savePost(post);
        return comment;
    }
}
