package com.example.MyBibliotheque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BibliothequeController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/book")
    public List<Book> index() {
        return bookRepository.findAll();
    }


    @GetMapping("/book/{id}")
    public Book show(@PathVariable int id){
        return bookRepository.findById(id).get();
    }
    @GetMapping("/book/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("description");
        String searchTitle = body.get("title");

        return bookRepository.findByDescriptionContainingOrTitleContaining(searchTerm, searchTitle);
    }


    @PostMapping("/book")
    public Book create(@RequestBody Book book) {

        return bookRepository.save(book);
    }

    @PutMapping("/book/{id}")
    public Book update(@PathVariable int id, @RequestBody Book newBook) {
        return bookRepository.findById(id)
                .map(book -> {

                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setDescription(newBook.getDescription());
                    return bookRepository.save(book);
                })
       .orElseGet(() -> {
           newBook.setId(id);
           return bookRepository.save(newBook);
       });
    }

    @DeleteMapping("book/{id}")
    public boolean delete(@PathVariable int id) {
        bookRepository.deleteById(id);
        return true;
    }
}
