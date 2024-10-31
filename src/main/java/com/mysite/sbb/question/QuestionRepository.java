package com.mysite.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

// <Qustion, Integer> : Question 엔터티로 리포지터리를 생성하며, Question 엔터티의 기본키는 Integer임
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);

    Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}
