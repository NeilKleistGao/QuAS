package com.quas.questionnaire.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class QuasQuestionnaireSchema {
    @Id
    private ObjectId schema_id;

    //TODO:

    public QuasQuestionnaireSchema() {
        this.schema_id = ObjectId.get();
    }
}
