package fullbuild.de.surveysitereloaded.controllers;

import fullbuild.de.surveysitereloaded.dto.TokenDTO;
import fullbuild.de.surveysitereloaded.repositories.UserRepository;
import fullbuild.de.surveysitereloaded.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController  {

    private final TokenManager tokenManager;
    private final UserRepository userRepository;

    @GetMapping("/request")
    public TokenDTO getToken(Authentication authentication) {
        return new TokenDTO(tokenManager.getTokenOrNew(userRepository.findByUsername(authentication.getName()).orElseThrow(RuntimeException::new)));
    }
}
