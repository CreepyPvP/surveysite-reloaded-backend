package fullbuild.de.surveysitereloaded.controller;

import fullbuild.de.surveysitereloaded.dto.TokenDTO;
import fullbuild.de.surveysitereloaded.repositories.UserRepository;
import fullbuild.de.surveysitereloaded.security.AuthenticationToken;
import fullbuild.de.surveysitereloaded.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController  {

    private final TokenManager tokenManager;
    private final UserRepository userRepository;

    @GetMapping("/request")
    public TokenDTO getToken(Authentication authentication) {
        return new TokenDTO(tokenManager.getNewToken(userRepository.findByUsername(authentication.getName()).orElseThrow(RuntimeException::new)));
    }

    @GetMapping("/validate")
    public ResponseEntity<TokenDTO> validateToken(Authentication authentication) {
        if(authentication instanceof AuthenticationToken) {
            System.out.println(((AuthenticationToken) authentication).getUser());
            return ResponseEntity.ok().body(new TokenDTO((AuthenticationToken) authentication));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
