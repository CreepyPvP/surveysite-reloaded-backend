package fullbuild.de.surveysitereloaded.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    //private final BasicRequestAuthenticationEntryPoint authenticationEntryPoint;
    private final TokenAuthenticationProvider tokenAuthenticationProvider;
    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    public WebSecurity(UserRepository userRepository, TokenAuthenticationProvider tokenAuthenticationProvider, TokenManager tokenManager) {
        this.userRepository = userRepository;
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
        this.tokenManager = tokenManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/debug").permitAll()

                .antMatchers(HttpMethod.GET, "/token/request").authenticated()
                .antMatchers(HttpMethod.POST, "/token/logout").authenticated()
                .antMatchers(HttpMethod.GET, "/survey/preview").authenticated()

                .anyRequest().denyAll()
                .and()
                .httpBasic();
                //.authenticationEntryPoint(authenticationEntryPoint);

        http
                .addFilterBefore(new TokenAuthenticationFilter(tokenManager), BasicAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.authenticationProvider(tokenAuthenticationProvider);
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserManager(userRepository);
    }

    @Bean
    public AuthenticationManager authenticationManagerProvider() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return encoder();
    }

    private PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
