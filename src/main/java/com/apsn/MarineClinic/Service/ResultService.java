package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Result;
import com.apsn.MarineClinic.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    //get all
    @GetMapping("/resultList")
    public List<Result> getAllResult(){
        return  resultRepository.findAll();
    }
    //get by seaman name

    //create
    //update
    //delete
}
