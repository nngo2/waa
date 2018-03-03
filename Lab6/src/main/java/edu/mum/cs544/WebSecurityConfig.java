package edu.mum.cs544;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("admin");
		auth.inMemoryAuthentication().withUser("sales").password("sales").roles("sales");
		auth.inMemoryAuthentication().withUser("accountant").password("accountant").roles("accountant");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/resources/**", "/index").permitAll()
			.antMatchers("/cars/**").hasAnyRole("admin", "sales")
			.antMatchers("/addCar").hasAnyRole("admin", "sales")
			.antMatchers("/books/**").hasAnyRole("admin", "accountant")
			.antMatchers("/addBook").hasAnyRole("admin", "accountant")
			.and()
		.formLogin()
			.loginPage("/login").failureUrl("/loginFailed").permitAll()
			.loginProcessingUrl("/j_spring_security_check")
			.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
			.logoutSuccessUrl("/index")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
	}

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
