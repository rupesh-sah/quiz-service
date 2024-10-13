
package com.rupesh.question_service.controlloer;
import com.rupesh.question_service.entities.QuestionEntities;
import com.rupesh.question_service.entities.QuestionWrapper;
import com.rupesh.question_service.entities.Response;
import com.rupesh.question_service.service.QuestionService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
//    get all the data
    @GetMapping("/allQuestions")
    public ResponseEntity<List<QuestionEntities>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    this is return status code

//    // post the data
//    @PostMapping("/post")
//    public ResponseEntity<QuestionEntities> createUser (@RequestBody QuestionEntities questionEntities){
//        QuestionEntities questionEntities1 = questionService.saveQuestion(questionEntities);
//        return ResponseEntity.status(HttpStatus.CREATED).body(questionEntities1);
//    }


//    @PostMapping("/post")
//    public String addQuestion(@RequestBody QuestionEntities questionEntities){
//        return questionService.addQuestion(questionEntities);
//    }

    //    with HttpStatus code
    @PostMapping("/post")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionEntities questionEntities) {
        return questionService.addQuestion(questionEntities);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////

//    find by category
////    without httpStatus code
//    @GetMapping("/category/{category}")
//    public List<QuestionEntities> getQuestionsByCategory(@PathVariable String category){
//        return questionService.getQuestionsByCategory(category);
//    }

//    with HttpStatus code

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionEntities>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    find by UserId
    @GetMapping("/{integer}")
    public ResponseEntity<QuestionEntities> getsinglevalue(@PathVariable Integer integer) {
        QuestionEntities questionEntities = questionService.getQuestionId(integer);
        return ResponseEntity.ok(questionEntities);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
// update
    @PutMapping("/{integer}")
    public ResponseEntity<QuestionEntities> updateQuestion(@PathVariable Integer integer,
                                                           @RequestBody QuestionEntities questionEntities) {
        QuestionEntities questionEntities1 = questionService.updateQuestion(questionEntities, integer);

        return ResponseEntity.ok(questionEntities1);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
//    deleteMapping
    @DeleteMapping("/{integer}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable Integer integer) {
        questionService.deleteByUserId(integer);
        return ResponseEntity.noContent().build();
    }

//*********************************************************************************************
//  generate
//    getQuestions (questionid)
//    getScore
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions) {
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionFromId(questionIds);

    }
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
    //*********************************************************************************************

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////

