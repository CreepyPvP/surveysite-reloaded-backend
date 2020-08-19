package fullbuild.de.surveysitereloaded.security;


import fullbuild.de.surveysitereloaded.exceptions.InvalidTokenException;
import fullbuild.de.surveysitereloaded.models.User;
import fullbuild.de.surveysitereloaded.repositories.UserRepository;
import fullbuild.de.surveysitereloaded.security.model.TokenModel;
import fullbuild.de.surveysitereloaded.security.model.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private static final int MAX_TOKEN_PER_USER = 5;
    private static final int TOKEN_LENGTH = 15;
    private static final String ALLOWED_CHARS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;


    public AuthenticationToken createToken(User user) {
        // to prevent spam
        if(tokenRepository.countByUser(user) >= MAX_TOKEN_PER_USER) {
            hardLogout(user);
        }

        String token = "";
        do {
            token = generateToken();
        } while(tokenRepository.existsByToken(token));

        AuthenticationToken authenticationToken = new AuthenticationToken(token, user);
        TokenModel tokenModel = new TokenModel(authenticationToken);
        tokenRepository.save(tokenModel);

        user.getTokenModel().add(tokenModel);
        userRepository.save(user);

        return authenticationToken;
    }

    public AuthenticationToken getNewToken(User user) {
        checkExpired(user);
        return createToken(user);
    }

    public AuthenticationToken getAuthentication(String token) throws InvalidTokenException {
        checkExpired(token);
        return tokenRepository.findByToken(token).orElseThrow(InvalidTokenException::new).toAuthenticationToken();
    }

    public boolean exists(AuthenticationToken authenticationToken) {
        return tokenRepository.existsByToken(authenticationToken.getToken());
    }

    private void checkExpired(String token) {
        tokenRepository.deleteByTokenAndExpirationDateBefore(token, new Date());
    }

    private void checkExpired(User user) {
        tokenRepository.deleteByUserAndExpirationDateBefore(user, new Date());
    }


    private void logout(String token) {
        tokenRepository.deleteByToken(token);
    }

    // Logs out from all devices
    private void hardLogout(User user) {
        tokenRepository.deleteAllByUser(user);
    }

    //------------------------------------------------------

    private String generateToken() {
        StringBuilder builder = new StringBuilder();
        Random r = new Random();
        for(int i = 0; i < TOKEN_LENGTH; i++) {
            builder.append(ALLOWED_CHARS.charAt(Math.abs(r.nextInt() % ALLOWED_CHARS.length())));
        }
        return builder.toString();
    }

}
