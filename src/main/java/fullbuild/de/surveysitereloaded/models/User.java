package fullbuild.de.surveysitereloaded.models;

import fullbuild.de.surveysitereloaded.configs.UserDetailsModel;
import fullbuild.de.surveysitereloaded.security.model.TokenModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private int id;
    
    private String username = "error";

    private String password = "error";

    @OneToMany(mappedBy = "user")
    private List<TokenModel> tokenModel = new ArrayList<>();


    public List<SimpleGrantedAuthority> getAuthorities() {
        return new ArrayList<SimpleGrantedAuthority>();
    }


    public UserDetailsModel toUserDetails() {
        return new UserDetailsModel(this);
    }

}
