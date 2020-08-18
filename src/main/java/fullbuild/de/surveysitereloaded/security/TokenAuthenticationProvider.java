package fullbuild.de.surveysitereloaded.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenManager tokenManager;

    public TokenAuthenticationProvider(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationToken authenticationToken = (AuthenticationToken) authentication;

        if(!tokenManager.exists(authenticationToken)) {
            return null;
        }

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AuthenticationToken.class);
    }

}
