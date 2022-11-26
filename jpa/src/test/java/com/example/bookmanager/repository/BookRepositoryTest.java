package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Book;
import com.example.bookmanager.domain.Member;
import com.example.bookmanager.domain.Publisher;
import com.example.bookmanager.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("책1");
        book.setAuthorId(1L);
        //book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();
        Member member = memberRepository.findByEmail("grace1@naver.com");

        System.out.println("Review : "+ member.getReviews());
        System.out.println("Book: "+ member.getReviews().get(0).getBook());
        System.out.println("Publisher: " +member.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenMember(), givenBook(givenPublisher()));

    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("출판사");

        return publisherRepository.save(publisher);
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("재밌는책");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Member givenMember(){
        return memberRepository.findByEmail("grace1@naver.com");
    }



    private void givenReview(Member member, Book book) {
        Review review = new Review();
        review.setTitle("책제목");
        review.setContent("유익한 내용");
        review.setScore(5.0f);
        review.setMember(member);
        review.setBook(book);

        reviewRepository.save(review);
    }
}