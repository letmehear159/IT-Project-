//package ITProject.example.WebSelling.config;
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor()
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    protected final String[] PUBLIC_ENDPOINTS = {"/users/", "/auth/**", "/auth/refresh"};
//    protected final String[] TESTING = {"/**"};
//
//    CustomJwtDecoder customJwtDecoder;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                //                                .requestMatchers(HttpMethod.GET, "/users/").hasRole(ROLE.ADMIN.name())
//                .requestMatchers(HttpMethod.POST, TESTING)
//                .permitAll()
//                .requestMatchers(HttpMethod.GET, TESTING)
//                .permitAll()
//                .anyRequest()
//                .authenticated());
//
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
//                        .decoder(customJwtDecoder)
//                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                .authenticationEntryPoint(new AuthenticationEntryPoint()));
//
//        return http.build();
//    }
//
//    @Bean
//    JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
//}
