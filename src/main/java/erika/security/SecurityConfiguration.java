package erika.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import erika.entity.User;
import erika.service.UserService;

@Configuration
	@EnableWebSecurity
	public class SecurityConfiguration extends WebSecurityConfiguration {

	    @Autowired
	    private UserService userService;

	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(username -> {
	            User user = userService.findByUsername(username);
	            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	        }).passwordEncoder(new BCryptPasswordEncoder());
	    }

	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeRequests()
	            .requestMatchers("/api/users/register").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .httpBasic();
	    }
	}
