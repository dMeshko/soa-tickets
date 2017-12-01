package finki.ukim.mk.soatickets.sts;

import finki.ukim.mk.soatickets.business.services.implementation.UsersService;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static finki.ukim.mk.soatickets.core.Constants.SIGN_IN_URL;
import static finki.ukim.mk.soatickets.core.Constants.SIGN_UP_URL;

/**
 * Created by DarkoM on 24.11.2017.
 */

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsersService usersService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                //.authorizeRequests().antMatchers(HttpMethod.POST, SIGN_IN_URL).permitAll()
                //.antMatchers("/api/users/").access("hasRole('admin')")
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager()))
                .addFilter(new AuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
