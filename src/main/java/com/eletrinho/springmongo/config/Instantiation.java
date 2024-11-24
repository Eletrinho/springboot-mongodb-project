package com.eletrinho.springmongo.config;

import com.eletrinho.springmongo.config.util.PasswordUtil;
import com.eletrinho.springmongo.dto.AuthorDTO;
import com.eletrinho.springmongo.entities.CommentDTO;
import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.entities.User;
import com.eletrinho.springmongo.repository.PostRepository;
import com.eletrinho.springmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "mariabrown","maria@gmail.com", PasswordUtil.getPasswordHash("maria"));
        User alex = new User(null, "Alex Green", "alexgreen","alex@gmail.com", PasswordUtil.getPasswordHash("alex"));
        User bob = new User(null,  "Bob Grey", "bobgrey","bob@gmail.com", PasswordUtil.getPasswordHash("bob"));

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post = new Post(null, "Bó viaja", new AuthorDTO(maria));
        post.getComments().add(new CommentDTO("Boa viagem", Instant.now(), new AuthorDTO(alex)));
        post.getComments().add(new CommentDTO("Aproveite", Instant.now(), new AuthorDTO(bob)));

        Post post2 = new Post(null, "acordei agora", new AuthorDTO(maria));
        post2.getComments().add(new CommentDTO("Tenha um ótimo dia", Instant.now(), new AuthorDTO(alex)));

        postRepository.saveAll(Arrays.asList(post, post2));

        maria.getPosts().addAll(Arrays.asList(post, post2));
        userRepository.save(maria);
    }
}
