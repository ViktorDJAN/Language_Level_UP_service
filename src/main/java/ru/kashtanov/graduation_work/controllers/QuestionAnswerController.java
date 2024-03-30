package ru.kashtanov.graduation_work.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.kashtanov.graduation_work.services.AnswerService;
import ru.kashtanov.graduation_work.services.QuestionService;
import ru.kashtanov.graduation_work.services.QuestionAnswerService;


import java.util.Map;

/**
 * QuestionAnswerController is intended for processing requests coming to url related to testing pages
 */
@Controller
@RequestMapping("/api/v1")
public class QuestionAnswerController {
    private final QuestionAnswerService questionAnswerService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    public QuestionAnswerController(QuestionAnswerService questionAnswerService,
                                    QuestionService questionService,
                                    AnswerService answerService) {
        this.questionAnswerService = questionAnswerService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/qaList")
    public String showAllQuestionsWithTheirsAnswers(Model model) {
        model.addAttribute("questionsBunch", questionAnswerService.getAllQuestionWithAnswersList());

        return "testing/testingPage";
    }


    @PostMapping("/resultForming")
    public String formResult(@RequestParam Map<String, String> chosenAnswersMap,
                             Model model, RedirectAttributes redirectAttributes) {
        System.out.println(chosenAnswersMap);
        redirectAttributes
                .addAttribute("correctResult", questionAnswerService.resultProcessing(chosenAnswersMap));
        return "redirect:/api/v1/result";
    }

    @GetMapping("/result")
    public String getResult(@ModelAttribute("correctResult") String some) {
        return "testing/resultPage";

    }
}
