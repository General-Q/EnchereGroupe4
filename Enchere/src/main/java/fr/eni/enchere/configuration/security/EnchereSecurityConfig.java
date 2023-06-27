package fr.eni.enchere.configuration.security;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;

import static org.springframework.security.config.Customizer.withDefaults;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class EnchereSecurityConfig {
	protected final Log logger = LogFactory.getLog(getClass());
	private final String SELECT_USER = "select email, password, 1 from membre where email=?";
	private final String SELECT_ROLES = "select m.email, r.role from MEMBRE m inner join ROLES r on r.IS_ADMIN = m.admin where m.email = ?";

	// Récupération des utilisateurs de l'application via la base de données
	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);

		return jdbcUserDetailsManager;
	}

	/*
	 * Tout le monde doit accéder à la vue principale 
	 * Restreindre l’accès des autres
	 * vues à un membre connecté pour le moment
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests(auth -> {
	        auth
	        // Permettre aux visiteurs d'accéder à la liste des enchères
	            .requestMatchers(HttpMethod.GET, "/accueil").permitAll()
	        // Permettre aux visiteurs de s'identifier
	            .requestMatchers(HttpMethod.GET, "/identification").permitAll()
	        // Accès à la vue principale
	            .requestMatchers("/").permitAll()
	        // Permettre à tous d'afficher correctement les images et le CSS
	            .requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()
	        // Pour toutes les autres vues, il faut être connecté
	            .anyRequest().authenticated();
	    });

	    http.formLogin(login -> login
	            .loginPage("/identification")
	            .permitAll()
	            .defaultSuccessUrl("/session"))
	        .logout(logout -> logout
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .deleteCookies("JSESSIONID")
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/")
	            .permitAll())
	        .httpBasic(withDefaults());

	    return http.build();
	}


}
