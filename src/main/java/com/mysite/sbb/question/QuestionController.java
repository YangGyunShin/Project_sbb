package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    // RequiredArgsConstructor을 통해 객체를 자동주입
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        // model 객체는 자바클래스와 템플릿 간의 연결고리를 역할함.
        // model 객체는 따로 생성할 필요 없이 컨트롤러의 메서드에 매개변수로 지정하기만 하면 됨.
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    // question_detail 에서 th:object를 이용해 AnserForm를 연결시켰기 때문에, AnswerForm 객체를 넘겨야 함.
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    // bindingresult : @valid 애너테이션으로 검증이 수행된 결과를 의미, 항상 @valid 매개변수 바로 뒤에 있어야 함.
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        } this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        // 질문 저장 후 질문목록으로 이동
        return "redirect:/question/list";
    }
}
