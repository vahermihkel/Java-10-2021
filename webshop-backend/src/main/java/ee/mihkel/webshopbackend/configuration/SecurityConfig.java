package ee.mihkel.webshopbackend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.signingkey}")
    private String jwtSecretKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthorizationFilter jwtFilter = new JwtAuthorizationFilter(authenticationManager());
        jwtFilter.setSecretKey(jwtSecretKey);

        http
                .csrf().disable()
                .addFilter(jwtFilter)
                .authorizeRequests()
                .antMatchers("/log").permitAll()
                .antMatchers("/signup").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
