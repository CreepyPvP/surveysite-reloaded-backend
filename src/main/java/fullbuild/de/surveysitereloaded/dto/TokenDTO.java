package fullbuild.de.surveysitereloaded.dto;

import fullbuild.de.surveysitereloaded.security.AuthenticationToken;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
public class TokenDTO {

    private String token;
    private Date expirationDate;
    private String username;
    private int userId;
    private Collection<GrantedAuthority> authorities;

    public TokenDTO(AuthenticationToken token) {
        this.token = token.getToken();
        this.expirationDate = token.getExpirationDate();
        this.username = token.getUser().getUsername();
        this.userId = token.getUser().getId();
        this.authorities = token.getAuthorities();
    }

}
