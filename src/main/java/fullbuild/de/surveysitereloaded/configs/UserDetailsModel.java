package fullbuild.de.surveysitereloaded.configs;

import fullbuild.de.surveysitereloaded.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsModel implements UserDetails {

    private String username;
    private String password;

    private List<SimpleGrantedAuthority> grantedAuthorities;

    public UserDetailsModel(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();

        grantedAuthorities = user.getAuthorities();
        //this.grantedAuthorities = user.getPermissionRank().getPermissions().stream().map(e  -> new SimpleGrantedAuthority(e.toString())).collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}