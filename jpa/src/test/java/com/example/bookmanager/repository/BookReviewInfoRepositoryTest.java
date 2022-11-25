package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCnt(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> "+ bookReviewInfoRepository.findAll() );
    }

    @Test
    void crudTest2() {
        givenBook();
        givenBookReviewInfo();

        Book result = bookRepository.findById(
                bookReviewInfoRepository
                        .findById(1L)
                        .orElseThrow(RuntimeException::new)
                        .getBookId()
        ).orElseThrow(RuntimeException::new);

        System.out.println(">>> "+ result);
    }

    private void givenBook(){
        Book book = new Book();
        book.setName("클린코드");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(">>> " +bookRepository.findAll());
    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCnt(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> "+ bookReviewInfoRepository.findAll());
    }
}