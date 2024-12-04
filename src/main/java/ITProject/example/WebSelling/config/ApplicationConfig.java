package ITProject.example.WebSelling.config;

import ITProject.example.WebSelling.entity.User;
import ITProject.example.WebSelling.enums.ROLE;
import ITProject.example.WebSelling.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
public class ApplicationConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource-driver-classname",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner app(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(ROLE.ADMIN.name());
                roles.add(ROLE.USER.name());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        //                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("admin account has been created with default password admin, please change admin password");
            }
        };
    }
}
