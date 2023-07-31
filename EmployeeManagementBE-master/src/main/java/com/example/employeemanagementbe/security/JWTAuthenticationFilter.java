package com.example.employeemanagementbe.security;

import com.example.employeemanagementbe.Model.user.User;
import com.example.employeemanagementbe.Service.Abstract.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired

    private UserAuthProvider _tokenGenerator;
    @Autowired

    private IUserService _userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=getJWTFromRequest(request);
        if(StringUtils.hasText(token) && _tokenGenerator.validateJwtToken(token)){
            String username=_tokenGenerator.getUsernameFromJWT(token);
            System.out.println(username);
            System.out.println(_userService.findByLogin(username));
            User userDetails = _userService.findByLogin(username).get();
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails.getLogin(),userDetails.getpassword());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);

    }

    private String getJWTFromRequest(HttpServletRequest request ) {
        String bearerToken=request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
