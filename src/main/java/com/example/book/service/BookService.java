package com.example.book.service;

import com.example.book.dto.ApiResponse;
import com.example.book.dto.BookDTO;
import com.example.book.entity.Attechment;
import com.example.book.entity.AttechmentContent;
import com.example.book.entity.Books;
import com.example.book.entity.User;
import com.example.book.entity.enums.Tili;
import com.example.book.repository.AttechmentContentRepository;
import com.example.book.repository.AttechmentRepository;
import com.example.book.repository.BookRepository;
import com.example.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AttechmentRepository attechmentRepository;
    private final AttechmentContentRepository attechmentContentRepository;
    private final UserRepository userRepository;



    public ApiResponse addBook(BookDTO bookDTO){
        Books books=new Books();
        books.setBookname(bookDTO.getBookname());
        books.setAuthor(bookDTO.getAuthor());
        books.setReference(bookDTO.getReference());
        books.setTili(bookDTO.getTili());
        books.setAttechmentContent(attechmentContentRepository.getById(bookDTO.getAttechmentContentId()));
        books.setAttechmentContentphoto(attechmentContentRepository.getById(bookDTO.getAttechmentContentPhotoId()));
        bookRepository.save(books);
        return new ApiResponse("saved",true);
    }


    public ApiResponse updete(Integer id,BookDTO bookDTO){

        Optional<Books> byId = bookRepository.findById(id);
        if (byId.isPresent()) {
            Books books = byId.get();
            Optional<AttechmentContent> byId1 = attechmentContentRepository.findById(id);
            Optional<Attechment> byId2 = attechmentRepository.findById(id);

            books.setBookname(bookDTO.getBookname());
            books.setAuthor(bookDTO.getAuthor());
            books.setReference(bookDTO.getReference());
            books.setTili(bookDTO.getTili());
            books.setAttechmentContent(attechmentContentRepository.getById(bookDTO.getAttechmentContentId()));
            books.setAttechmentContentphoto(attechmentContentRepository.getById(bookDTO.getAttechmentContentPhotoId()));
            bookRepository.save(books);
            return new ApiResponse("Updete",true);
        }else {
            return new ApiResponse("Bazada mavjud emas",false);
        }
    }
    public ApiResponse dalete(Integer id){

        bookRepository.deleteById(id);
        attechmentRepository.deleteById(id);
        attechmentContentRepository.deleteById(id);
        return new ApiResponse("delete",true);
    }




    public ApiResponse getOneBook(String bookname){
        Optional<Books> byBookname = bookRepository.getByBookname(bookname);
        if (byBookname.isPresent()){
        return new ApiResponse("Mana",true);
        }else {
            return new ApiResponse("Bunday kitob mavjud emas",false);
        }

    }


    public ApiResponse downloadBook(Integer id){


        Optional<Books> byId1 = bookRepository.findById(id);
        if (!byId1.isPresent()) return new ApiResponse("Not found",false);
        Books books = byId1.get();

        Optional<Attechment> byId = attechmentRepository.findById(books.getId());
        if (!byId.isPresent()) return new ApiResponse("Not found",false);
        Attechment attechment = byId.get();
        Optional<AttechmentContent> byAttechmentId = attechmentContentRepository.findByAttechmentId(attechment.getId());
        if (!byAttechmentId.isPresent()) return new ApiResponse("Not found",false);

        AttechmentContent attechmentContent = byAttechmentId.get();
        ResponseEntity<byte[]> body = ResponseEntity.ok()
                .contentType(MediaType.valueOf(attechment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "file")
                .body(attechmentContent.getBytes());

        List<Books> booksList=new ArrayList<>();



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        if (!byUsername.isPresent()) return new ApiResponse("Not found",false);


        //        booksList.add(books);
//        User user1 = byUsername.get();
//        user1.setBooks(books);



        return new ApiResponse("Success",true,books);


    }


    public ApiResponse getByBooknameAndAuthor(String bookname,String author){

        Optional<Books> byBooknameAndAuthor = bookRepository.getByBooknameAndAuthor(bookname, author);
        if (byBooknameAndAuthor.isPresent()) {
            return new ApiResponse("Mana",true,byBooknameAndAuthor.get());
        }else {
            return new ApiResponse("MAvjud emas",false);
        }
    }

    public ApiResponse getByTili(String tili) {

        Tili of = Tili.valueOf(tili);

        for (Tili value : Tili.values()) {
            if (value.ordinal() == of.ordinal()) {
        Optional<Books> byTili = bookRepository.getByTili(of);

        return new ApiResponse("Mana", true, byTili.get());
            }
            else {continue;}
        }
        return new ApiResponse("Bazada mavjud emas",false);

    }





}
