package fullbuild.de.surveysitereloaded.controller;

import fullbuild.de.surveysitereloaded.dto.UserProfileDTO;
import fullbuild.de.surveysitereloaded.models.User;
import fullbuild.de.surveysitereloaded.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("profile/{id}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable("id") int id) {
        Optional<User> userOp = userRepository.findById(id);
        if(userOp.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(new UserProfileDTO(userOp.get()));
    }

}