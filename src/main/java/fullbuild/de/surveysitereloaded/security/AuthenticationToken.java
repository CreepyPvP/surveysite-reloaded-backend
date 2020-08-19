package fullbuild.de.surveysitereloaded.security;


import fullbuild.de.surveysitereloaded.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Date;

public class AuthenticationToken extends AbstractAuthenticationToken {

    /**
     * tokens are valid for TOKEN_EXPIRATION_TIME_DAYS days
     */
    public static final int TOKEN_EXPIRATION_TIME_DAYS = 4;

    private final String token;
    private final User user;

    private Date expirationDate = new Date();

    public AuthenticationToken(String token, User user) {
        super(user.getAuthorities());
        this.user = user;
        this.token = token;

        this.expirationDate.setTime(this.expirationDate.getTime() + TOKEN_EXPIRATION_TIME_DAYS * 24 * 60 * 60 * 1000);
    }

    public AuthenticationToken(String token, User user, Date expirationDate) {
        super(user.getAuthorities());
        this.user = user;
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {return expirationDate; }

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
