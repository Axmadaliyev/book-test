package com.example.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String username,password;
//    private List<Integer> bookId;

    private String role;

}
