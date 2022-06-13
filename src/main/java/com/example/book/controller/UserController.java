package com.example.book.controller;

import com.example.book.dto.ApiResponse;
import com.example.book.dto.UserDTO;
import com.example.book.entity.User;
import com.example.book.repository.UserRepository;
import com.example.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        Optional<User> byId = userRepository.findById(id);

        return ResponseEntity.status(byId.isEmpty() ?
                HttpStatus.NOT_FOUND : HttpStatus.OK).body(byId.orElse(new User()));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.ok().body("User not Found");
        }
        userRepository.delete(user.get());
        return ResponseEntity.ok().body("Deleted");
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity add(@RequestBody UserDTO dto) {
        ApiResponse add = userService.add(dto);
        return ResponseEntity.ok().body(add);
    }


}
