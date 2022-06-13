package com.example.book.entity;

import com.example.book.entity.enums.Tili;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Books {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bookname,reference,author;



    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private AttechmentContent attechmentContent;


    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private AttechmentContent attechmentContentphoto;


    @Column(name = "tili")
    @Enumerated(EnumType.STRING)
    private Tili tili;













}
