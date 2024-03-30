package ru.kashtanov.graduation_work.secutiry;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kashtanov.graduation_work.models.UserEntity;
import ru.kashtanov.graduation_work.repositories.UserEntityRepository;

import java.util.Optional;

/**
 * UserEntityDetailsService is used to obtain user authentication and authorization information.
 */
@Component
public class UserEntityDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public UserEntityDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userEntityRepository.findByUsername(username);
        UserEntityUserDetails userEntityUserDetails = new UserEntityUserDetails(user);
        return userEntityUserDetails;
    }
}