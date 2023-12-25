package com.neo4j.demo.user;

import com.neo4j.demo.security.User;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public String me(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest request) {
        User user = userService.create(request);

        UserResponse responseUser = new UserResponse(user.getName(), user.getUsername(), user.getRoles());

        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }
}
