package fullbuild.de.surveysitereloaded.controller;

import fullbuild.de.surveysitereloaded.models.User;
import fullbuild.de.surveysitereloaded.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/debug")
@RequiredArgsConstructor
public class DebugController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity<User> test() {
        User bob = User.builder()
                .username("Bob")
                .password(passwordEncoder.encode("hello"))
                .build();
        bob.init();
        userRepository.save(bob);
        return ResponseEntity.ok().body(bob);
    }

}
