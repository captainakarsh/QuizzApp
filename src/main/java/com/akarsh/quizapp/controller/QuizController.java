package com.akarsh.quizapp.controller;

import com.akarsh.quizapp.model.Question;
import com.akarsh.quizapp.model.QuestionWrapper;
import com.akarsh.quizapp.model.Response;
import com.akarsh.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title)
    {
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id)
    {
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId, @RequestBody List<Response> responses)
    {
        return quizService.submitQuiz(quizId,responses);
    }
}
