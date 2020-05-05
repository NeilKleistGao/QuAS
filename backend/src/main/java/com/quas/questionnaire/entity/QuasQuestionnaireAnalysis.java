package com.quas.questionnaire.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class QuasQuestionnaireAnalysis {
    @Id
    private ObjectId analysis_id;
    private String name;

    //TODO:

    public QuasQuestionnaireAnalysis() {
        this.analysis_id = ObjectId.get();
    }
}
