package fullbuild.de.surveysitereloaded.models;

import fullbuild.de.surveysitereloaded.Permission;
import fullbuild.de.surveysitereloaded.configs.UserDetailsModel;
import fullbuild.de.surveysitereloaded.security.model.TokenModel;
import fullbuild.de.surveysitereloaded.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    private String username = "error";

    private String password = "error";

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TokenModel> tokenModel = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private PermissionRank permissionRank;


    public List<SimpleGrantedAuthority> getAuthorities() {
        return new ArrayList<SimpleGrantedAuthority>();
    }

    public UserDetailsModel toUserDetails() {
        return new UserDetailsModel(this);
    }


    public void init() {
        if(permissionRank == null) permissionRank = BeanUtil.getBean(PermissionRank.class);
    }
}
