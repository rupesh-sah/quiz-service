//package com.rupesh.question-service.entities;
package com.rupesh.question_service.entities;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private Integer id;
    private String response;
}
