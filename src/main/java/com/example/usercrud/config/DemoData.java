package com.example.usercrud.config;


import com.example.usercrud.user.User;
import com.example.usercrud.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoData {
    @Bean
    CommandLineRunner seed(UserRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(User.builder().username("Damjan").email("damjan@gmail.com").fullName("Damjan Pavlovic").build());
                repo.save(User.builder().username("Petar").email("petar@gmail.com").fullName("Petar Franceskovic").build());
            }
        };
    }
}
