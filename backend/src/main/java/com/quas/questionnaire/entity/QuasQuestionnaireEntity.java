package com.quas.questionnaire.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collation = "questionnaire")
public class QuasQuestionnaireEntity {
    @Id
    private ObjectId questionnaire_id;
    private String title;
    private String author;
    private Date start_time, end_time;

    private QuasQuestionnaireSchema schema;
    private List<QuasQuestionnaireResult> resultList;
    private List<QuasQuestionnaireAnalysis> analysisList;
}
