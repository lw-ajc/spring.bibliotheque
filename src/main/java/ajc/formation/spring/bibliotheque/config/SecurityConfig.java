package ajc.formation.spring.bibliotheque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//@formatter:off
		return http.antMatcher("/api/**")
				.csrf().disable()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // faut le mettre ?????
//				.and()
				.authorizeRequests()
					.anyRequest().permitAll()
//					.anyRequest().hasAnyRole("ADMIN")
//					.anyRequest().hasAnyRole("ADHERENT")
//					.antMatchers(HttpMethod.OPTIONS).permitAll()
//					.antMatchers(HttpMethod.GET, "api/accueil_admin").hasAnyRole("ADMIN")
//					.antMatchers(HttpMethod.GET, "api/accueil_adherent").hasAnyRole("ADHERENT")
//					.antMatchers(HttpMethod.GET, "api/accueil").permitAll()
//					.antMatchers(HttpMethod.GET, "/api/compte/login/check/**").permitAll()
//					.antMatchers(HttpMethod.POST,"/api/client/inscription").anonymous()
//					.antMatchers(HttpMethod.POST, "/api/commande").hasAnyRole("USER")
//					.antMatchers(HttpMethod.GET).authenticated()
//					.anyRequest().hasAnyRole("ADMIN")
				.and()
				.httpBasic()
				.and()
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}