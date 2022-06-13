package com.example.book.service;

import com.example.book.dto.ApiResponse;
import com.example.book.dto.UserDTO;
import com.example.book.entity.Books;
import com.example.book.entity.User;
import com.example.book.repository.BookRepository;
import com.example.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookRepository bookRepository;

    @SneakyThrows
    public ApiResponse add(UserDTO dto) {

//        List<Books> booksList=new ArrayList<>();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User principal = (User) authentication.getPrincipal();
//        RoleEnum anEnum = principal.getRoleEnum();

//        if (anEnum.equals(RoleEnum.ADMIN)) {


        User user = new User();


        //        for (Integer integer : dto.getBookId()) {
//            Optional<Books> byId = bookRepository.findById(integer);
//            Books books = byId.get();
//            booksList.add(books);
//        }
//        user.setBooks(booksList);
//        User save = userRepository.save(user);

        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User save = userRepository.save(user);

//        }else {
//
//            return new ApiResponse("Sizda bunday huqqu yo'q!",false);

//        }


        return new ApiResponse("Success",true);


    }



}



