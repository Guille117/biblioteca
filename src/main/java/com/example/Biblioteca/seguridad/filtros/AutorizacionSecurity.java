package com.example.Biblioteca.seguridad.filtros;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Biblioteca.seguridad.jwt.SecurityUtils;
import com.example.Biblioteca.service.MUsuarioSecurityService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutorizacionSecurity extends OncePerRequestFilter{

    @Autowired
    private SecurityUtils utilsSecu;
    @Autowired
    private MUsuarioSecurityService usuServ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
        String token = request.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer")){
            token = token.substring(7);
        

            if(utilsSecu.isValidKey(token)){
                String alias = utilsSecu.getUserNameFromToken(token);
                UserDetails userDetails = usuServ.loadUserByUsername(alias);
                UsernamePasswordAuthenticationToken autetenti = 
                    new UsernamePasswordAuthenticationToken(alias, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autetenti);
                
            }
        }
        filterChain.doFilter(request, response);
    }
    
}
