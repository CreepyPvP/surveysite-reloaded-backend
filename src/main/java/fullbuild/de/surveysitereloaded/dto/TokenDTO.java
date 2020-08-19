package fullbuild.de.surveysitereloaded.dto;

import fullbuild.de.surveysitereloaded.security.AuthenticationToken;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {

    private String token;
    private String username;

    public TokenDTO(AuthenticationToken token) {
        this.token = token.getToken();
    }

}
