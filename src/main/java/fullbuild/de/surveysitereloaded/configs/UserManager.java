package fullbuild.de.surveysitereloaded.configs;

import fullbuild.de.surveysitereloaded.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserManager implements UserDetailsService {

    private UserRepository userRepository;


    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsModel userModel = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username)).toUserDetails();
        return userModel;
    }


}