package com.example.Biblioteca.seguridad.filtros;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.Biblioteca.modelo.MUsuario;
import com.example.Biblioteca.seguridad.jwt.SecurityUtils;


public class AutenticacionSecurity extends UsernamePasswordAuthenticationFilter{;

    private SecurityUtils secUtil;

    public AutenticacionSecurity(SecurityUtils secUtil){
        this.secUtil = secUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        MUsuario usuario = null;
        String alias = "";
        String contraseña = "";

        try{
            usuario = new ObjectMapper().readValue(request.getInputStream(), MUsuario.class);
            alias = usuario.getAlias();
            contraseña = usuario.getContraseña();
        }catch(StreamReadException e){
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }


        UsernamePasswordAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(alias, contraseña);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
                                        FilterChain chain, Authentication autenticacion){

        User user =(User) autenticacion.getPrincipal();
        String token = secUtil.generarToken(user.getUsername());

        response.addHeader("Authorization", token);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("mensaje", "autenticación valida");
        httpResponse.put("Username", user.getUsername());

        try{
            response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
            response.setStatus(200);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().flush();
        }catch(java.io.IOException e){
            throw new RuntimeException(e);
        }

        try{
            super.successfulAuthentication(request, response, chain, autenticacion);
        }catch(java.io.IOException e){
            throw new RuntimeException(e);
        }catch(ServletException e){
            throw new RuntimeException(e);
        }
        

    }
    

    private final ObjectMapper ob = new ObjectMapper();
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            AuthenticationException exception)throws IOException, ServletException, JsonProcessingException, java.io.IOException{

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        Map<String, Object> responseBody = new HashMap<>();

        responseBody.put("error", "Fallo de autenticación");
        responseBody.put("message", "Alias o contraseña incorrectos");

        response.getWriter().write(ob.writeValueAsString(responseBody));
        
    }
}
