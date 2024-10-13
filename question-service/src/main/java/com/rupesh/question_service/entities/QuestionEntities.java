
package com.rupesh.question_service.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@Table(name = "questionEntities111")
public class QuestionEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;
    private String category;
}
