package com.example.book.controller;

import com.example.book.dto.ApiResponse;
import com.example.book.dto.BookDTO;
import com.example.book.repository.AttechmentContentRepository;
import com.example.book.repository.AttechmentRepository;
import com.example.book.repository.BookRepository;
import com.example.book.repository.UserRepository;
import com.example.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final AttechmentContentRepository attechmentContentRepository;
    private final AttechmentRepository attechmentRepository;
    private final UserRepository userRepository;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity addBook(@RequestBody BookDTO bookDTO){

        ApiResponse apiResponse = bookService.addBook(bookDTO);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){

        bookService.dalete(id);
        return ResponseEntity.ok("delete");
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity updete(@PathVariable Integer id,@RequestBody BookDTO bookDTO){

        ApiResponse updete = bookService.updete(id, bookDTO);
        return ResponseEntity.status(updete.isSuccess()? 200:409).body(updete);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPER_ADMIN')")
    @GetMapping("/bookdownloaduser/{id}")
    @ResponseBody
    public ResponseEntity download(@PathVariable Integer id){
        ApiResponse apiResponse = bookService.downloadBook(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 200:409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/searchbookname")
    @ResponseBody
    public ResponseEntity SearchBookName(@RequestParam(name = "bookname") String bookname,@RequestParam(name = "author") String author) {

        ApiResponse booknameAndAuthor = bookService.getByBooknameAndAuthor(bookname, author);
        return ResponseEntity.ok(booknameAndAuthor);

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/searchtili")
    @ResponseBody
    public ResponseEntity SearchTili(@RequestParam(name = "tili") String tili) {
        ApiResponse byTili = bookService.getByTili(tili);
        return ResponseEntity.ok(byTili);

    }



}
