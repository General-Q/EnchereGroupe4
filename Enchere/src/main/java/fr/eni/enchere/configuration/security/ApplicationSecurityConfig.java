package fr.eni.enchere.configuration.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	private final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder() ;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}

	  @Autowired
	  public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
		  String userByMailQuery = "SELECT email, mot_de_passe, 1 FROM UTILISATEURS WHERE email = ?;";
		  String userByUsernameQuery = "SELECT pseudo, mot_de_passe, 1 FROM utilisateurs WHERE pseudo = ?";
		  
		  // Connexion via le mail
		  auth.jdbcAuthentication()
		  		.dataSource(dataSource)
		  		.passwordEncoder(passwordEncoder)
		  		.usersByUsernameQuery(userByMailQuery)
		  		.authoritiesByUsernameQuery("SELECT ?,'admin' ")
		  		;
		  
		  // Connexion via le pseudo
		  auth.jdbcAuthentication()
	            .dataSource( dataSource )
	            .passwordEncoder( passwordEncoder )
	            .usersByUsernameQuery(userByUsernameQuery)
	            .authoritiesByUsernameQuery( "SELECT ?, 'admin' " ) 
	            ;
	        
	  }
	
		@Bean
		SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests(auth -> {
				auth
						//Permettre aux visiteurs d'accéder à certaines pages
						.requestMatchers(HttpMethod.GET, "/", "/accueil", "/inscription").permitAll()
						//Permettre aux membres d'accéder au formulaire ajout de film
						//.requestMatchers(HttpMethod.GET, "/VueAjouter").hasAuthority("MEMBRE")
						// Accès à la vue principale
						.requestMatchers("/enregistrerProfil").permitAll()
						// Permettre à tous d'afficher correctement les images et CSS
						.requestMatchers("/css/*").permitAll()
						.requestMatchers("/layout.css").permitAll()
						.requestMatchers("/images/*").permitAll()
						.requestMatchers("/resources/*").permitAll()
						// Il faut être connecté pour toutes autres URLs
						.anyRequest().authenticated();
			});
			//formulaire de connexion par défaut
			//http.formLogin(Customizer.withDefaults());
			
			  // Customiser le formulaire
			http.formLogin(form -> {
				form.defaultSuccessUrl("/accueil").permitAll();
			});

			// /logout --> vider la session et le contexte de sécurité
			http.logout(logout -> 
					logout
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.deleteCookies("JSESSIONID")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/").permitAll())
			//.httpBasic(Customizer.withDefaults())
			;

			return http.build();
			
		}  
}