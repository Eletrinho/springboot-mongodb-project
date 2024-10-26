package com.eletrinho.springmongo.config;

import com.eletrinho.springmongo.dto.AuthorDTO;
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

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post = new Post(null, Instant.now(), "Partiu viagem", "Bó viaja", new AuthorDTO(maria));
        Post post2 = new Post(null, Instant.now(), "Bom dia", "acordei agora", new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(post, post2));

        maria.getPosts().addAll(Arrays.asList(post, post2));
        userRepository.save(maria);
    }
}
