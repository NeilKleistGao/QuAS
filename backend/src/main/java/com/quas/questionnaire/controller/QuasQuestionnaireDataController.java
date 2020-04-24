package com.quas.questionnaire.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/questionnaire/data")
public class QuasQuestionnaireDataController {

    @RequestMapping(value = "/{qid}", method = RequestMethod.POST)
    public Object postQuestionnaireResult(@PathVariable(name = "qid")String qid, Object result) {
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/{qid}", method = RequestMethod.GET)
    public Object getResultData(@PathVariable(name = "qid")String qid) {
        return null;
    }

    @RequestMapping(value = "/analysis/{qid}", method = RequestMethod.POST)
    public Object modifyAnalysis(@PathVariable(name = "qid") String qid, Object params) {
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/analysis/{qid}", method = RequestMethod.GET)
    public Object getAllAnalysis(@PathVariable(name = "qid") String qid) {
        return null;
    }
}
