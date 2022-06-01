package com.in28minutes.springboot.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Since Spring Security 5.7.0, WebSecurityConfigurerAdapter has been deprecated and should use a component-based security configuration.
// @Configuration
@EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig {
	// Authentication : User --> Roles
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("user1").password("secret1")
//				.roles("USER").and().withUser("admin1").password("secret1")
//				.roles("USER", "ADMIN");
//	}

	// We used the method User.withDefaultPasswordEncoder() for readability and course purpose.
	// It is not intended for PRODUCTION environment, and instead we recommend hashing your passwords externally
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("admin1")
				.password("secret1")
				.roles("ADMIN", "USER")
				.build();
		UserDetails adminUser = User.withDefaultPasswordEncoder()
				.username("user1")
				.password("secret1")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(adminUser, user);
	}

	// Authorization : Role -> Access
	// survey -> USER
	// In Spring Security 5.4 introduced the ability to configure HttpSecurity by creating a SecurityFilterChain bean.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests((authorize) -> {
					try {
						authorize
								.antMatchers("/surveys/**").hasRole("USER")
								.antMatchers("/users/**").hasRole("USER")
								.antMatchers("/**").hasRole("ADMIN")
								.antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
								.and().csrf().disable()
								.headers().frameOptions().disable();

					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				})
				.httpBasic(withDefaults());

		return http.build();
	}

	// Authorization : Role -> Access
	// survey -> USER
//	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().and().authorizeRequests().antMatchers("/surveys/**")
//				.hasRole("USER").antMatchers("/users/**").hasRole("USER")
//				.antMatchers("/**").hasRole("ADMIN").and().csrf().disable()
//				.headers().frameOptions().disable();
//	}

}
