package com.example.book.component;

import com.example.book.entity.User;
import com.example.book.entity.enums.RoleEnum;
import com.example.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    String mode;

    final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRoleEnum(RoleEnum.USER);
            userRepository.save(user);

            User admin = new User();
            admin.setRoleEnum(RoleEnum.ADMIN);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(admin);

            User superadmin = new User();
            superadmin.setRoleEnum(RoleEnum.SUPER_ADMIN);
            superadmin.setUsername("superadmin");
            superadmin.setPassword(passwordEncoder.encode("superadmin"));
            userRepository.save(superadmin);
        }


    }
}
