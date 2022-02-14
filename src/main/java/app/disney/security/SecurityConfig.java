package app.disney.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import app.disney.security.filter.CustomAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().anyRequest().permitAll();
		http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));

	}

	@Bean @Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();

	}
}
