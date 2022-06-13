package com.example.book.controller;

import com.example.book.dto.LoginDTO;
import com.example.book.entity.User;
import com.example.book.repository.UserRepository;
import com.example.book.security.JwtProvider;
import com.example.book.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final AuthService authService;

   private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDTO loginDTO){
//        UserDetails userDetails = authService.loadUserByUsername(loginDTO.getUserName());
//        if (userDetails != null) {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    loginDTO.getUserName(), loginDTO.getPassword()));
////
//
//            String token = jwtProvider.generateToken(loginDTO.getUserName());
//            return ResponseEntity.ok().body(token);
//        }
//        else {
//            return ResponseEntity.status(401).body("Kirish taqiqlangan!!!");
//        }



         Optional<User> byUsername = userRepository.findByUsername(loginDTO.getUserName());
        Map<Object,Object> map=new HashMap<>();

        if (!byUsername.isPresent()){
            throw new UsernameNotFoundException("Bunday foydalanuvchi mavjud emas");
        }else {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(),loginDTO.getPassword()));

            String s = jwtProvider.generateToken(loginDTO.getUserName());
            map.put("token",s);
            map.put("user",byUsername.get());
            return ResponseEntity.ok(map);
        }

    }


}
