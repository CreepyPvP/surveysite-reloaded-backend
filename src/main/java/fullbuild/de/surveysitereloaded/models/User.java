package fullbuild.de.surveysitereloaded.models;

import fullbuild.de.surveysitereloaded.configs.UserDetailsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;
    
    private String username = "error";

    private String password = "error";

    public List<SimpleGrantedAuthority> getAuthorities() {
        return new ArrayList<SimpleGrantedAuthority>();
    }


    public UserDetailsModel toUserDetails() {
        return new UserDetailsModel(this);
    }

}
