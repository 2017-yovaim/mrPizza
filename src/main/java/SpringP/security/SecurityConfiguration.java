package SpringP.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	private DataSource dataSource;
	
	protected void congfigure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/h/*", "/style.css", "/h2/**")
		.permitAll()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/login", "h/ingredients/add", "h/pizzas/add")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll();
		
		http.csrf()
		.ignoringAntMatchers("/login", "h/ingredients/add", "h/pizzas/add");
		http.headers()
		.frameOptions()
		.sameOrigin();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication()
		/*.withDefaultSchema()
		.withUser(User.withUsername("Yuria").roles("USER").password(passwordEncoder().encode("Londor")))
		*/.dataSource(dataSource);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
