package com.flying.seeker.review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class Review {

    @Id
    private String id;
    private String comentary;
    private Integer stars;

}
