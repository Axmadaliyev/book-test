package com.example.book.repository;

import com.example.book.entity.AttechmentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttechmentContentRepository extends JpaRepository<AttechmentContent,Integer> {
Optional<AttechmentContent> findByAttechmentId(Integer uuid);
}
