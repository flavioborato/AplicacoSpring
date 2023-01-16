package br.com.alura.mvc.mudi;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
		
	 @Autowired
	    private DataSource dataSource;

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//			http
//				.authorizeRequests()
//				.anyRequest()
//				.authenticated()
//				.and()
//				.httpBasic();  // forma basica para login
							
			http
			.csrf().disable()
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.defaultSuccessUrl("/usuario/pedido", true)
				.permitAll()
			)
			.logout((logout) -> logout.permitAll().logoutSuccessUrl("/home"));

		return http.build();
		}

		
	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public JdbcUserDetailsManager users(PasswordEncoder encoder){
	    	
	        @SuppressWarnings("unused")
			UserDetails user = 
	                        User.builder()
	                            .username("fernando")
	                            .password(encoder.encode("123456"))
	                            .roles("ADM")
	                            .build();
	        
	        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//	        jdbcUserDetailsManager.createUser(user); // cria usuario
	        return jdbcUserDetailsManager;
	    }
		
		

//		@Bean
//		public UserDetailsService userDetailsService() {
//			UserDetails user =
//				 User.withDefaultPasswordEncoder()
//					.username("flavio")
//					.password("123456")
//					.roles("ADM")
//					.build();
//
//			return new InMemoryUserDetailsManager(user);
//		}  // uma forma de fazer login
	
	
	
}
