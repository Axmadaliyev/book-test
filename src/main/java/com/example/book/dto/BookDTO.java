package com.example.book.dto;

import com.example.book.entity.enums.Tili;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    private String bookname,reference,author;
    private Integer attechmentContentId;
    private Integer attechmentContentPhotoId;

    private Tili tili;


}
