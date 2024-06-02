package csc340project.example.springio.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;


@Service
public class UserLoginService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userDetailsService().loadUserByUsername(username);
    }

    public UserDetailsService userDetailsService(){

        UserDetails admin
                = User.builder()
                .username("Collin")
                .password(passwordEncoder().encode("this-pw"))
                .roles("SYSADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
