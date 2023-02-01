package com.dashboard.api.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dashboard.api.security.services.CusUsersDetailsService;

//import com.dashboard.api.security.services.CusUsersDetails;
//import com.dashboard.api.security.services.CusUsersDetailsService;

//import lombok.Data;

//@Data
public class TokenFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CusUsersDetailsService cusUsersDetailsService;

    private static final Logger logger=(Logger) LoggerFactory.getLogger(TokenFilter.class);

    public static Logger getLogger() {
        return logger;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt=parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUsernameJwtToken(jwt);

                UserDetails userDetails=cusUsersDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticate=new UsernamePasswordAuthenticationToken
                (userDetails, null, userDetails.getAuthorities());

                authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentification", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String jwt =jwtUtils.getJwtCookie(request);
        return jwt;
    }
}
