package com.akarsh.quizapp.service;

import com.akarsh.quizapp.dao.QuestionDao;
import com.akarsh.quizapp.dao.QuizDao;
import com.akarsh.quizapp.model.Question;
import com.akarsh.quizapp.model.QuestionWrapper;
import com.akarsh.quizapp.model.Quiz;
import com.akarsh.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for(Question q:questions)
        {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestiontitle(),q.getOption1(),q.getOption2(),q.getOption3());
            questionWrappers.add(qw);
        }

        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Integer quizId, List<Response> responses) {
        Integer ans = 0;
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
        int i = 0;
        for(Response r : responses)
        {
            if(r.getResponse().equals(questions.get(i).getRightanswer())) ans ++;
            i++;
        }

        return new ResponseEntity<>(ans,HttpStatus.OK);
    }
}
