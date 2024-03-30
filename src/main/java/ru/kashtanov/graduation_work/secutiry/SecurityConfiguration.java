package ru.kashtanov.graduation_work.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import ru.kashtanov.graduation_work.repositories.UserEntityRepository;

/**
 * SecurityConfiguration is an important class respondent for Security functions in that project
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserEntityRepository userEntityRepository;
    private final AppSuccessHandler appSuccessHandler;
    private final PasswordConfig passwordEncoder;


    @Autowired
    public SecurityConfiguration(PasswordConfig passwordEncoder,
                                 UserEntityRepository userEntityRepository,
                                 AppSuccessHandler appSuccessHandler) {
        this.passwordEncoder = passwordEncoder;
        this.userEntityRepository = userEntityRepository;
        this.appSuccessHandler = appSuccessHandler;
    }


    @Bean
    UserDetailsService userDetailsService() {
        return new UserEntityDetailsService(userEntityRepository);
    }

    /**
     * Here we can adjust security features
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()// добавляет тоекн к request
                .authorizeRequests()//это метод в Spring Security, который ограничивает доступ на основе реализаций RequestMatcher.
                .antMatchers("/register/**", "/login/**").permitAll()
                .antMatchers("/api/v1/student_scope/**").hasRole("STUDENT")
                .antMatchers("/api/v1/tutor_scope/**").hasRole("TUTOR")
                .antMatchers("/api/v1/admin_scope/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(appSuccessHandler)
                .and()
                .build();
    }

    /**
     * Is needed to authenticate a username and password.
     return authenticationProvider;
     * @return authenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();// получаем данные о пользователе
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());// кодирование декодирование пароля
        return authenticationProvider;
    }

    /**
     * determines which appropriate authentication provider the request should go to.
     * //возвращает экземпляр полный Authentication с установленным в true флагом аутентификации . В противном случае, если
     *     // принципал недействителен, будет выдано исключение AuthenticationException .
     *     // В последнем случае он возвращает null , если не может принять решение. ``
     * @param config
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    //возвращает экземпляр полный Authentication с установленным в true флагом аутентификации . В противном случае, если
    // принципал недействителен, будет выдано исключение AuthenticationException .
    // В последнем случае он возвращает null , если не может принять решение. ``
}
