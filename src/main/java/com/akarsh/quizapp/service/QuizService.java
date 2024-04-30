package com.akarsh.quizapp.service;

import com.akarsh.quizapp.dao.QuestionDao;
import com.akarsh.quizapp.dao.QuizDao;
import com.akarsh.quizapp.model.Question;
import com.akarsh.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionByCategory(category.toUpperCase(),numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("SUCESS", HttpStatus.CREATED);

    }
}
