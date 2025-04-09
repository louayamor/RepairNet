package itbs.louayamor.repairnet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeRequests(authz -> authz
                .requestMatchers("/api/equipment/**").permitAll()  // Allow access to the equipment API without authentication
                .anyRequest().authenticated())  // Other endpoints require authentication
            .formLogin(form -> form
                .loginPage("/login").permitAll())  // Custom login page
            .logout(logout -> logout.permitAll());  // Logout is permitted for all users
        return http.build();
    }

    // Password encoder bean (for password encoding)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // User details service (for in-memory authentication or custom authentication)
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> User.builder()
                .username(username)
                .password(passwordEncoder().encode("password"))  // Replace with actual password logic
                .roles("USER")
                .build();
    }
}
