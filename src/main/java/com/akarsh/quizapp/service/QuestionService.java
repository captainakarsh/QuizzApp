package com.akarsh.quizapp.service;

import com.akarsh.quizapp.model.Question;
import com.akarsh.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategory(category.toUpperCase());
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Sucess", HttpStatus.CREATED);
        }catch (Exception e){
            e.getStackTrace();
        }
        return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
    }
}
