package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;

// <Qustion, Integer> : Question 엔터티로 리포지터리를 생성하며, Question 엔터티의 기본키는 Integer임
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
