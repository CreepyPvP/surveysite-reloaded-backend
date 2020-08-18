package fullbuild.de.surveysitereloaded.security;

import fullbuild.de.surveysitereloaded.exceptions.InvalidTokenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;

    public TokenAuthenticationFilter(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("SecurityToken");

        if(token == null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        try {
            Authentication authentication = tokenManager.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (InvalidTokenException e) {
            // Token invalid or expired
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
