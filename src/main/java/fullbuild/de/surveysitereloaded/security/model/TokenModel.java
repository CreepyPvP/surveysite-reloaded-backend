package fullbuild.de.surveysitereloaded.security.model;

import fullbuild.de.surveysitereloaded.models.User;
import fullbuild.de.surveysitereloaded.security.AuthenticationToken;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class TokenModel {

    @Id
    private String token;

    @ManyToOne
    private User user;

    public TokenModel() {

    }

    public TokenModel(AuthenticationToken authenticationToken) {
        this.token = authenticationToken.getToken();
        this.user = authenticationToken.getUser();
    }

    public AuthenticationToken toAuthenticationToken() {
        return new AuthenticationToken(token, user);
    }

}
