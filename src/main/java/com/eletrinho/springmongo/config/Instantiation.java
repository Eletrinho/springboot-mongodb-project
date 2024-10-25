package com.eletrinho.springmongo.config;

import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.entities.User;
import com.eletrinho.springmongo.repository.PostRepository;
import com.eletrinho.springmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post = new Post(null, Instant.now(), "Partiu viagem","Bó viaja", maria);
        Post post2 = new Post(null, Instant.now(), "Bom dia","acordei agora", maria);

        postRepository.saveAll(Arrays.asList(post, post2));
        userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
}
