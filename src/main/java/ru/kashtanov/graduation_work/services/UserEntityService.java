package ru.kashtanov.graduation_work.services;

import org.springframework.stereotype.Component;
import ru.kashtanov.graduation_work.models.UserEntity;
import ru.kashtanov.graduation_work.secutiry.PasswordConfig;
import ru.kashtanov.graduation_work.repositories.StudentRepository;
import ru.kashtanov.graduation_work.repositories.RoleRepository;
import ru.kashtanov.graduation_work.repositories.UserEntityRepository;

import javax.persistence.EntityNotFoundException;

@Component
public class UserEntityService {
    private final RoleRepository roleRepository;
    private final PasswordConfig passwordEncoder;
    private final UserEntityRepository userEntityRepository;
    private final StudentRepository studentRepository;

    public UserEntityService(RoleRepository roleRepository,
                             PasswordConfig passwordEncoder,
                             UserEntityRepository userEntityRepository,
                             StudentRepository studentRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userEntityRepository = userEntityRepository;
        this.studentRepository = studentRepository;
    }


    public UserEntity authenticate(String username, String password) {


        UserEntity userEntity = userEntityRepository
                .findByUsernameAndAndPassword(username, password).orElseThrow(() -> new EntityNotFoundException("it is not found!!"));

        return userEntity;
    }

    public UserEntity findUserEntityByUserName(String username) {
        return userEntityRepository.findByUsername(username).orElse(null);
    }
}
