package com.quas.questionnaire.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class QuasQuestionnaireResult {
    @Id
    private ObjectId result_id;
    private String IP_from;
    private Date submit_time;

    //TODO:

    public QuasQuestionnaireResult() {
        this.result_id = ObjectId.get();
    }
}
