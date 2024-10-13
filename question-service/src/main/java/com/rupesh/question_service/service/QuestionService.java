//package com.rupesh.question-service.service;
package com.rupesh.question_service.service;

import com.rupesh.question_service.dao.QuestionDao;

import com.rupesh.question_service.entities.QuestionEntities;
import com.rupesh.question_service.entities.QuestionWrapper;
import com.rupesh.question_service.entities.Response;
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////
// get all
//    public List<QuestionEntities> getAllQuestions(){
//        return questionDao.findAll();
//    }

//    exception handel
    public ResponseEntity<List<QuestionEntities>> getAllQuestions(){
        try {
            return new  ResponseEntity<>( questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new  ResponseEntity<>(new  ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////

////    post all the data
//    public QuestionEntities saveQuestion( QuestionEntities questionEntities){
//        return questionDao.save(questionEntities);
//    }

    //    both are methode are same

    public ResponseEntity <String> addQuestion(QuestionEntities questionEntities) {
       questionDao.save(questionEntities);
       try {
           return new ResponseEntity<>( "success", HttpStatus.CREATED);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return new ResponseEntity<>( "Not created", HttpStatus.BAD_REQUEST);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//    without httpStatus code
//    public List<QuestionEntities> getQuestionsByCategory(String category){
//        return questionDao.findByCategory(category);
//    }

//    both are working

    //    with HttpStatus code
        public ResponseEntity<List<QuestionEntities>>getQuestionsByCategory(String category){
        try {
            return new ResponseEntity<>( questionDao.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return new ResponseEntity<>(new ArrayList<>() ,HttpStatus.BAD_REQUEST);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public QuestionEntities findById(Integer id) {
//        return questionDao.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
//
//    }


    public QuestionEntities getQuestionId(Integer integer) {
        return questionDao.findById(integer).orElseThrow(()-> new RuntimeException("user not found"));
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public QuestionEntities updateQuestion(QuestionEntities questionEntities, Integer integer) {
        QuestionEntities questionEntities1 = questionDao.findById(integer).orElseThrow(()->
                new RuntimeException("User not found with id: " + integer));
        questionEntities1.setQuestionTitle(questionEntities.getQuestionTitle());
        questionEntities1.setOption1(questionEntities.getOption1());
        questionEntities1.setOption2(questionEntities.getOption2());
        questionEntities1.setOption3(questionEntities.getOption3());
        questionEntities1.setOption4(questionEntities.getOption4());
        questionEntities1.setRightAnswer(questionEntities.getRightAnswer());
        questionEntities1.setDifficultyLevel(questionEntities.getDifficultyLevel());
        questionEntities1.setCategory(questionEntities.getCategory());

        return questionDao.save(questionEntities1);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void  deleteByUserId(Integer integer) {
        questionDao.deleteById(integer);

    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questionEntities1 = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
        return  new ResponseEntity<>(questionEntities1, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<QuestionEntities> questionEntities = new ArrayList<>();

        for (Integer id : questionIds){
            questionEntities.add(questionDao.findById(id).get());
        }

        for (QuestionEntities questionEntities1 : questionEntities){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(questionEntities1.getId());
            wrapper.setQuestionTitle(questionEntities1.getQuestionTitle());
            wrapper.setOption1(questionEntities1.getOption1());
            wrapper.setOption2(questionEntities1.getOption2());
            wrapper.setOption3(questionEntities1.getOption3());
            wrapper.setOption4(questionEntities1.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);

    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right = 0;

        for (Response response : responses){
            QuestionEntities questionEntities = questionDao.findById(response.getId()).get();
            if (response.getResponse().equals(questionEntities.getRightAnswer()))
                right ++;



        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////