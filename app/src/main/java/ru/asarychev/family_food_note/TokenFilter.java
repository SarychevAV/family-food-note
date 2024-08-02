package ru.asarychev.family_food_note;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final JwtCore jwtCore;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = null;
        String jwt = null;
        UserDetails userDetails;
        UsernamePasswordAuthenticationToken auth;
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            jwt = headerAuth.substring(7);
        }
        if (jwt != null) {
            try {
                username = jwtCore.getNameFromJwt(jwt);
            } catch (ExpiredJwtException e) {
                System.out.println("Время жизни JWT токена истекло");
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                userDetails = userDetailsService.loadUserByUsername(username);
                auth = new UsernamePasswordAuthenticationToken(userDetails, jwt, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
