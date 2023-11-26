package com.example.Biblioteca.seguridad;


import java.util.Map;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Biblioteca.seguridad.filtros.AutenticacionSecurity;
import com.example.Biblioteca.seguridad.filtros.AutorizacionSecurity;
import com.example.Biblioteca.seguridad.jwt.SecurityUtils;
import com.example.Biblioteca.service.MUsuarioSecurityService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class ConfigSecurity{
    @Autowired 
    private SecurityUtils utilidadesSecur;
    @Autowired
    private MUsuarioSecurityService usuServ;
    @Autowired
    private AutorizacionSecurity autorizacionSecur;


    @Bean
    public SecurityFilterChain fil(HttpSecurity http, AuthenticationManager adminAutenti) throws Exception{
        AutenticacionSecurity autenticacion = new AutenticacionSecurity(utilidadesSecur);
        autenticacion.setAuthenticationManager(adminAutenti);
        //autenticacion.setFilterProcessesUrl("/login");  // indica la ruta de logueo

        http
        .csrf(csrf -> csrf.disable())
        .formLogin(log -> log.loginPage("/login"))
        .authorizeHttpRequests(aut ->{
            aut.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            aut.requestMatchers("/login","/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**" ).permitAll();     
            aut.anyRequest().authenticated();                                                                              
        })
        .sessionManagement(sesion ->{
            sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        })
        .addFilter(autenticacion)
        .addFilterBefore(autorizacionSecur, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder codificadora(){
        return new BCryptPasswordEncoder();
    }

    @Bean       // administra la autenticación del usuario 
    AuthenticationManager autenticacion(HttpSecurity security, PasswordEncoder codificadora) throws Exception{
        return security.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(usuServ)        // obtiene los datos del usuario
        .passwordEncoder(codificadora)              // obtiene la contraseña
        .and()
        .build();
    }
}
