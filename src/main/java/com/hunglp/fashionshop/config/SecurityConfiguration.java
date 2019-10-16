package com.hunglp.fashionshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.hunglp.fashionshop.repository.NguoiDungRepository;
import com.hunglp.fashionshop.security.JwtAuthenticationFilter;
import com.hunglp.fashionshop.security.JwtAuthorizationFilter;
import com.hunglp.fashionshop.service.security.ChiTietNguoiDungPrincipalService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private NguoiDungRepository nguoiDungRepository;
	private ChiTietNguoiDungPrincipalService chitietNguoiDungPrincipalService;
	
	
	 public SecurityConfiguration(NguoiDungRepository nguoiDungRepository, ChiTietNguoiDungPrincipalService chiTietNguoiDungPrincipalService) {
		this.nguoiDungRepository = nguoiDungRepository;
		this.chitietNguoiDungPrincipalService = chiTietNguoiDungPrincipalService;
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").authorities("ADMIN_ACTION")
			.and()
			.withUser("hunglp").password(passwordEncoder().encode("hunglp")).roles("USER").authorities("USER_ACTION");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//remove csrf sate in session because jwt don't need
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
			.addFilter(new JwtAuthorizationFilter(authenticationManager(), this.nguoiDungRepository))
			.authorizeRequests()
//			.and()
//			.formLogin().loginPage("/login").permitAll()
//			.and()
//			.logout()
//			.logoutSuccessUrl("/login") .logoutUrl("/logout") .permitAll()
//			.and()
//			.antMatchers("/home").permitAll()
			.antMatchers("/home").permitAll()
//			.antMatchers(HttpMethod.POST,"/login").permitAll()
			.antMatchers("/api/user").hasAuthority("USER_ACTION")
			.antMatchers("/api/admin").hasAuthority("ADMIN_ACTION");
			
			
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		daoAuthenticationProvider.setUserDetailsService(this.chitietNguoiDungPrincipalService);
//		
//		return daoAuthenticationProvider;
//		
//	}
}
