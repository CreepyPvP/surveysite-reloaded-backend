package fullbuild.de.surveysitereloaded.security;


import fullbuild.de.surveysitereloaded.models.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthenticationToken extends AbstractAuthenticationToken {


    private final String token;
    private final User user;


    public AuthenticationToken(String token, User user) {
        super(user.getAuthorities());
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

}
