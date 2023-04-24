package mx.edu.utez.extraordinario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        final String DEF_PASS = "cisco123";

        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password(DEF_PASS)
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("capturista")
                .password(DEF_PASS)
                .roles("TRANSCRIBER")
                .build();
        UserDetails user3 = User.withDefaultPasswordEncoder()
                .username("cliente")
                .password(DEF_PASS)
                .roles("CLIENT")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final String ADMIN = "ADMIN";
        final String TRANSCRIBER = "TRANSCRIBER";
        final String CLIENT = "CLIENT";
        final String ANONYMOUS = "ANONYMOUS";

        http.authorizeHttpRequests(requests -> {
            requests.requestMatchers("/assets/**", "/").permitAll();
            requests.requestMatchers("/capturistas").hasAnyRole(ADMIN);
            requests.requestMatchers("/clientes").hasAnyRole(ADMIN, TRANSCRIBER);
            requests.requestMatchers("/pedidos").hasAnyRole(ADMIN, TRANSCRIBER, CLIENT);
            requests.requestMatchers("/productos").hasAnyRole(CLIENT, ANONYMOUS);
            requests.requestMatchers("/registrarse").hasAnyRole(ANONYMOUS);
            requests.anyRequest().authenticated();
        });
        http.formLogin().permitAll();
        http.formLogin().defaultSuccessUrl("/");
        http.logout().permitAll();
        return http.build();
    }
}
