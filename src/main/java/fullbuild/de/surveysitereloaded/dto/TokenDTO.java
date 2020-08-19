package fullbuild.de.surveysitereloaded.dto;

import fullbuild.de.surveysitereloaded.security.AuthenticationToken;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TokenDTO {

    private String token;
    private Date expirationDate;
    private String username;

    public TokenDTO(AuthenticationToken token) {
        this.token = token.getToken();
        this.expirationDate = token.getExpirationDate();
    }

}
