package com.example.book.repository;

import com.example.book.entity.Attechment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttechmentRepository extends JpaRepository<Attechment, Integer> {

}
