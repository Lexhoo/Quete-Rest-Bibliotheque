package com.example.MyBibliotheque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

  List<Book> findByDescriptionContainingOrTitleContaining(String description, String title);
}
