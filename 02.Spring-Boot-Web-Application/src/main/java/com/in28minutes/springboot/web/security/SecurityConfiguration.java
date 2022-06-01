package com.in28minutes.springboot.web.security;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
@EnableWebSecurity
public class SecurityConfiguration {
	//Create User - in28Minutes/dummy
//	@Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication()
//            .passwordEncoder(NoOpPasswordEncoder.getInstance())
//        		.withUser("in28Minutes").password("dummy")
//                .roles("USER", "ADMIN");
//    }

    // We used the method User.withDefaultPasswordEncoder() for readability and course purpose.
    // It is not intended for PRODUCTION environment, and instead we recommend hashing your passwords externally
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("in28Minutes")
                .password("dummy")
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    // In Spring Security 5.4 introduced the ability to configure HttpSecurity by creating a SecurityFilterChain bean.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((authorize) -> {
                    try {
                        authorize
                                .antMatchers("/login", "/h2-console/**").permitAll()
                                .antMatchers("/", "/*todo*/**").hasRole("USER")
                                .and().formLogin()
                                .and().csrf().disable()
                                .headers().frameOptions().disable();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });


        return http.build();
    }
	
//	Previous version that securing all endpoints with HTTP basic
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
//                .antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
//                .formLogin();
//
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//    }

}
