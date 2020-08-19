package fullbuild.de.surveysitereloaded.security.model;

import fullbuild.de.surveysitereloaded.models.User;
import fullbuild.de.surveysitereloaded.security.AuthenticationToken;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class TokenModel {


    @Id
    private String token;

    @ManyToOne
    private User user;

    private Date expirationDate;

    public TokenModel(AuthenticationToken authenticationToken) {
        this.token = authenticationToken.getToken();
        this.user = authenticationToken.getUser();
        this.expirationDate = authenticationToken.getExpirationDate();
    }

    public AuthenticationToken toAuthenticationToken() {
        return new AuthenticationToken(token, user, expirationDate);
    }

    @Override
    public String toString() {
        return "{\n" +
                "   token: " + token + ",\n" +
                "   user: " + user.getUsername() + ",\n" +
                "   expirationDate: " + expirationDate + "\n" +
                "}";
    }

}
