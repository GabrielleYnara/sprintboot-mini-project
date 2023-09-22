package com.example.app.seed;

import com.example.app.model.Category;
import com.example.app.model.Profile;
import com.example.app.model.User;
import com.example.app.repository.CategoryRepository;
import com.example.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * For testing purpose, this class injects data into our application.
 */
@Component
public class SeedData implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public SeedData(@Lazy PasswordEncoder passwordEncoder, //loads on-demand
                    UserRepository userRepository,
                    CategoryRepository categoryRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Everytime the application starts, this method will be invoked
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmailAddress("gabrielleynara@ymail.com");
        user.setPassword(passwordEncoder.encode("gaby1234"));
        user.setProfile(new Profile());

        Category category = new Category();
        category.setName("Morning Routine");
        category.setDescription("Habits related to morning routine");
        category.setUser(user);
        userRepository.save(user);
        categoryRepository.save(category);
    }

}
