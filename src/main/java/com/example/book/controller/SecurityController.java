package com.example.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController("/api/user")
public class SecurityController {

//    @GetMapping("/username")
//    @ResponseBody
//    public String currentUserName(Principal principal) {
//        return principal.getName();
//    }
//
//    @GetMapping("/username")
//    @ResponseBody
//    public String currentUserName(Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        return String.valueOf(userDetails.getAuthorities());
//    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public ResponseEntity currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return ResponseEntity.ok(principal.getName());
    }



}
