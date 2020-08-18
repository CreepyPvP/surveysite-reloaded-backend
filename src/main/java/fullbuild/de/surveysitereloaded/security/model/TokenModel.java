package fullbuild.de.surveysitereloaded.security.model;

import fullbuild.de.surveysitereloaded.models.User;
import fullbuild.de.surveysitereloaded.security.AuthenticationToken;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class TokenModel {

    /**
     * tokens are valid for TOKEN_EXPIRATION_TIME_DAYS days
     */
    public static final int TOKEN_EXPIRATION_TIME_DAYS = 4;


    @Id
    private String token;

    @OneToOne
    private User user;

    private Date expirationDate = new Date();

    public TokenModel(AuthenticationToken authenticationToken) {
        this.token = authenticationToken.getToken();
        this.user = authenticationToken.getUser();
        this.expirationDate = new Date();
        this.expirationDate.setTime(this.expirationDate.getTime() + TOKEN_EXPIRATION_TIME_DAYS * 24 * 60 * 60 * 1000);
    }

    public AuthenticationToken toAuthenticationToken() {
        return new AuthenticationToken(token, user);
    }

}
