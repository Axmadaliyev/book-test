package com.example.book.security;

import com.example.book.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;


    private final AuthService authService;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//        //validate expired kimga tegishli
//        if (jwtProvider.validateToken(token)) {
//            if (jwtProvider.expireToken(token)) {
//                String username = jwtProvider.getUsernameFromToken(token);
//                UserDetails userDetails = authService.loadUserByUsername(username);
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//
//
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//
//        }
////        System.out.println(SecurityContextHolder.getContext().getAuthentication());
//
//        doFilter(request, response, filterChain);
//
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().startsWith("/api/auth/")) {
            String token = request.getHeader("Authorization");
            token = token.substring(7);
            if (jwtProvider.validateToken(token)) {
                if (jwtProvider.expireToken(token)) {
                    String usernameFromToken = jwtProvider.getUsernameFromToken(token);
                    UserDetails userDetails = authService.loadUserByUsername(usernameFromToken);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, "", userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
}
            }
        }
        doFilter(request, response, filterChain);
    }
}
