package ch.zli.m223.punchclock.filters;

import ch.zli.m223.punchclock.Config;
import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.PrincipalUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ApplicationUser applicationUser =
                new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    applicationUser.getUsername(),
                    applicationUser.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult) {
        String token =
            JWT.create()
                .withSubject(((PrincipalUser) authResult.getPrincipal()).getUsername())
                .withClaim("id", ((PrincipalUser) authResult.getPrincipal()).getId())
                .withExpiresAt(new Date(Config.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(Config.API_KEY.getBytes()));
        response.addHeader("Authorization", "Bearer " + token);
    }
}
