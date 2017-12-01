package finki.ukim.mk.soatickets.sts;

import com.fasterxml.jackson.databind.ObjectMapper;
import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import finki.ukim.mk.soatickets.models.user.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static finki.ukim.mk.soatickets.core.Constants.EXPIRATION_TIME;
import static finki.ukim.mk.soatickets.core.Constants.HEADER_STRING;
import static finki.ukim.mk.soatickets.core.Constants.TOKEN_PREFIX;
import static finki.ukim.mk.soatickets.core.Constants.SECRET;

/**
 * Created by DarkoM on 24.11.2017.
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            creds.getClaims())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        AuthenticatedUser user = ((AuthenticatedUser) auth.getPrincipal());

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getId());
        claims.put("roles", user.getRoles());
        claims.put("authorities", user.getAuthorities());

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) {

        // see comments in Servlet API around using sendError as an alternative
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
