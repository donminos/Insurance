package com.tdmobile.template.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tdmobile.template.services.security.UsuarioServiceSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccesHandler loginSuccesHandler;
	
	@Autowired
	private MyBasicAuthenticationEntryPoint basicAuthenticationPoint;
		
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioServiceSecurity usuarioServiceSecurity;
	
	/*@Autowired
	private CsrfSecurityRequestMatcher protectionMatcher;*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
		//http.csrf().requireCsrfProtectionMatcher(protectionMatcher); //se desabilitaria para REST
		http.csrf().ignoringAntMatchers("/api/**");
		http.authorizeRequests()
		.antMatchers("/","/css/**","/js/**","/images/**","/api/**").permitAll()
		//.antMatchers("/lista").hasAnyRole("USER")
		.anyRequest().authenticated()
		.and().formLogin().successHandler(loginSuccesHandler).defaultSuccessUrl("/")
		.loginPage("/login").permitAll().and().logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/Access_Denied");
		
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		/*PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserBuilder user = User.builder().passwordEncoder(encoder::encode);
		build.inMemoryAuthentication().withUser(user.username("admin").password("1234").roles("ADMIN", "USER"))
				.withUser(user.username("user").password("1234").roles("USER"));*/
		build.userDetailsService(usuarioServiceSecurity)
		.passwordEncoder(passwordEncoder);
		
		
	}
}
