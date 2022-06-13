package com.example.book.controller;

import com.example.book.entity.Attechment;
import com.example.book.entity.AttechmentContent;
import com.example.book.repository.AttechmentContentRepository;
import com.example.book.repository.AttechmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class AttechmentController {
final AttechmentContentRepository attechmentContentRepository;
final AttechmentRepository attechmentRepository;

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Integer saveFile(MultipartHttpServletRequest request) throws IOException {
    MultipartFile file = request.getFile("ketmon");
    Attechment attechment=null;
    if (file !=null || !file.isEmpty()){
        Attechment attechment1=new Attechment();
        attechment1.setContentType(file.getContentType());
        attechment1.setName(file.getOriginalFilename());
        attechment1.setSize(file.getSize());
         attechment = attechmentRepository.save(attechment1);


        AttechmentContent attechmentContent=new AttechmentContent();
        attechmentContent.setBytes(file.getBytes());
        attechmentContent.setAttechment(attechment);
        attechmentContentRepository.save(attechmentContent);

    }
    return attechment.getId();
}

@PreAuthorize("hasAuthority('ADMIN')")
@PostMapping("/uploadMany")
     public List<Integer> saveFileMany(MultipartHttpServletRequest request) throws IOException {
    Iterator<String> fileNames = request.getFileNames();
    List<Integer> integers=new ArrayList<>();
    while (fileNames.hasNext()) {
        List<MultipartFile> files = request.getFiles(fileNames.next());

        for (MultipartFile file : files) {
            Attechment attechment=null;
            if (file!=null || !file.isEmpty()){
                Attechment attechment1=new Attechment();
                attechment1.setSize(file.getSize());
                attechment1.setName(file.getOriginalFilename());
                attechment1.setContentType(file.getContentType());
                attechment=attechmentRepository.save(attechment1);

                AttechmentContent attechmentContent=new AttechmentContent();
                attechmentContent.setBytes(file.getBytes());
                attechmentContent.setAttechment(attechment);
                attechmentContentRepository.save(attechmentContent);
                integers.add(attechment.getId());
            }
        }
    }
    return integers;
}

@GetMapping("/{id}")
    public HttpEntity<?> download(@PathVariable Integer id){
    Optional<Attechment> byId = attechmentRepository.findById(id);
    Attechment attechment = byId.get();
    Optional<AttechmentContent> byAttechmentId = attechmentContentRepository.findByAttechmentId(attechment.getId());
    AttechmentContent attechmentContent = byAttechmentId.get();
    return ResponseEntity.ok()
            .contentType(MediaType.valueOf(attechment.getContentType()))
            .header(HttpHeaders.CONTENT_DISPOSITION,"file")
            .body(attechmentContent.getBytes());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable Integer id){
        attechmentContentRepository.deleteById(id);
        attechmentRepository.deleteById(id);
        return ResponseEntity.ok().body("Success");
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity daletefinAll(){
        attechmentContentRepository.deleteAll();
        attechmentRepository.deleteAll();
        return ResponseEntity.ok().body("Success!!");
    }

}

