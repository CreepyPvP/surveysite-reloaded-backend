package fullbuild.de.surveysitereloaded.security;


import fullbuild.de.surveysitereloaded.exceptions.InvalidTokenException;
import fullbuild.de.surveysitereloaded.models.User;
import fullbuild.de.surveysitereloaded.security.model.TokenModel;
import fullbuild.de.surveysitereloaded.security.model.TokenRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class TokenManager {

    private static final int TOKEN_LENGTH = 15;
    private static final String ALLOWED_CHARS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";

    private final TokenRepository tokenRepository;

    public TokenManager(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public AuthenticationToken createToken(User user) {
        String token = "";
        do {
            token = generateToken();
        } while(tokenRepository.existsByToken(token));

        AuthenticationToken authenticationToken = new AuthenticationToken(token, user);
        tokenRepository.save(new TokenModel(authenticationToken));
        return authenticationToken;
    }

    public AuthenticationToken getTokenOrNew(User user) {
        Optional<TokenModel> userTokenOptional = tokenRepository.findByUser(user);
        if(userTokenOptional.isPresent()) {
            return userTokenOptional.get().toAuthenticationToken();
        }

        return createToken(user);
    }

    public AuthenticationToken getAuthentication(String token) throws InvalidTokenException {
        return tokenRepository.findByToken(token).orElseThrow(InvalidTokenException::new).toAuthenticationToken();
    }

    public boolean exists(AuthenticationToken authenticationToken) {
        return tokenRepository.existsByToken(authenticationToken.getToken());
    }

    public void invalidateToken(AuthenticationToken token) {
        if(!exists(token)){
            return;
        }

        tokenRepository.deleteById(token.getToken());
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
