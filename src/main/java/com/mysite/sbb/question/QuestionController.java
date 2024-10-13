package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    // RequiredArgsConstructor을 통해 객체를 자동주입
    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        // model 객체는 자바클래스와 템플릿 간의 연결고리를 역할함.
        // model 객체는 따로 생성할 필요 없이 컨트롤러의 메서드에 매개변수로 지정하기만 하면 됨.
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping(value = "/question/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
}
