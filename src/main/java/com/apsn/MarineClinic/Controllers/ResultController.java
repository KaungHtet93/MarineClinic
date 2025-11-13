package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Model.Result;
import com.apsn.MarineClinic.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;

    //get all
    @GetMapping("/resultService")
    public List<Result> getAllResult(){
        return resultService.getAllResult();
    }
}
