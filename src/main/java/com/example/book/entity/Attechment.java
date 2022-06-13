package com.example.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Attechment {
    @Id
//    @Type(type = "org.hibernate.type.PostgresUUIDType")
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
//    private UUID id=UUID.randomUUID();
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    private Timestamp createdAt;
    private String name;
    private long size;
    private String contentType;

}
