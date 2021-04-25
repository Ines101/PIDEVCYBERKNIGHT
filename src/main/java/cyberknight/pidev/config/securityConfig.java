package cyberknight.pidev.config;

import lombok.AllArgsConstructor;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import cyberknight.pidev.service.UserDetailServiceImpl;

@EnableWebSecurity
@AllArgsConstructor
public class securityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailServiceImpl userDetailsService;

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()// hasAuthority("ADMIN")
				// .antMatchers("/user").
				// role w user
				.antMatchers("/role/**").hasAuthority("ADMIN")
				.antMatchers("/event/**").hasAuthority("ADMIN")
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/accountVerification/**").permitAll()
				.antMatchers("/ads/**").authenticated()
				.antMatchers("/category/**").hasAuthority("ADMIN")
				.antMatchers("/ads/AcceptAds").hasAuthority("ADMIN")
				.antMatchers("/ads/DeclineAds").hasAuthority("ADMIN")
				.antMatchers("/product/**").permitAll()
				.antMatchers("/product/Addproduct").hasAuthority("ADMIN")
				.antMatchers("/product/Deleteproduct/**").hasAuthority("ADMIN")
				.antMatchers("/product/Modifyproduct").hasAuthority("ADMIN")
				.antMatchers("/product/AddLike").authenticated()
				.antMatchers("/product/Dislike").authenticated()
				.antMatchers("/login").anonymous()
				.antMatchers("/signup").anonymous()
				.antMatchers("/reclamation/**").hasAuthority("CLIENT")
				.antMatchers("/user/**").authenticated()
				.antMatchers("/").permitAll()
				.anyRequest().anonymous()
//				.and().logout(logout -> logout.logoutUrl("/user/logout").addLogoutHandler((request, response, auth) -> {
//	                for (Cookie cookie : request.getCookies()) {
//	                    String cookieName = cookie.getName();
//	                    Cookie cookieToDelete = new Cookie(cookieName, null);
//	                    cookieToDelete.setMaxAge(0);
//	                    response.addCookie(cookieToDelete);
//	                }
//				}).addLogoutHandler(new SecurityContextLogoutHandler()).clearAuthentication(true).permitAll());
		;

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
