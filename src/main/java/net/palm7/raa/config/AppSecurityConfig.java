package net.palm7.raa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.palm7.raa.model.Role;
import net.palm7.raa.service.UserContextService;

@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	Environment env;

	@Configuration
	@Order(1)
	public static class BaseSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		private UserContextService userService;

		@Autowired
		public BaseSecurityConfigurationAdapter(UserContextService userService) {
			this.userService = userService;
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userService);
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/resources/**", "/", "/login/**", "/register/**", "/cars", "/rest/**")
					.permitAll().antMatchers("/users/**").hasAuthority(Role.Roles.ADMIN.getRole())
					.antMatchers("/reservations/**", "/cars/*/available/**", "/cars/new")
					.hasAnyAuthority(Role.Roles.ADMIN.getRole(), Role.Roles.EMPLOYEE.getRole()).anyRequest()
					.authenticated().and().formLogin().loginPage("/login").usernameParameter("email")
					.defaultSuccessUrl("/").failureUrl("/login/failure").and().rememberMe().and().logout()
					.logoutUrl("/logout").deleteCookies("remember-me");
		}

	}
}