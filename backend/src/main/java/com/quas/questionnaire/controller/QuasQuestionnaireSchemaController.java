package com.quas.questionnaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/questionnaire/schema")
public class QuasQuestionnaireSchemaController {

    @ResponseBody
    @RequestMapping(value = "/{qid}" ,method = RequestMethod.GET)
    public Object getQuestionnaireSchema(@PathVariable("qid") String qid) {
        List<String> qid_list = new ArrayList<String>();
        qid_list.add(qid);
        return qid_list;
    }

    @ResponseBody
    @RequestMapping(value = "/{qid}", method = RequestMethod.POST)
    public Object updateQuestionnaireSchema() {
        return "OK";
    }
}
