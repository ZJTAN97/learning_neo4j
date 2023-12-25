package com.neo4j.demo.user;

import com.neo4j.demo.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User create(UserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setUsername(request.username());
        user.setRoles(request.roles());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        return user;
    }
}
