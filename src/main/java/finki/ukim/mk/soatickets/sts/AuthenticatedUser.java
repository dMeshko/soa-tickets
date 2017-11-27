package finki.ukim.mk.soatickets.sts;

import finki.ukim.mk.soatickets.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by DarkoM on 24.11.2017.
 */
public class AuthenticatedUser implements UserDetails {
    private User user;

    public AuthenticatedUser(User user){
        this.user = user;
    }

    public long getId(){
        return user.getId();
    }

    public List<String> getRoles(){
        return user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getClaims();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
