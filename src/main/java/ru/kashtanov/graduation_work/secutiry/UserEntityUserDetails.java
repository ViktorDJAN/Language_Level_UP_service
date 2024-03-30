package ru.kashtanov.graduation_work.secutiry;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kashtanov.graduation_work.models.UserEntity;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * UserEntityUserDetails provides necessary info of users
 */
public class UserEntityUserDetails implements UserDetails {

    private UserEntity userEntity;

    public UserEntityUserDetails(Optional<UserEntity> userEntity) {
        this.userEntity = userEntity.orElseThrow(() -> new EntityNotFoundException("it is not found!!"));

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().getRoleName()));
        return roles;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
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
