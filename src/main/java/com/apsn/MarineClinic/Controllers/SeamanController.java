package com.apsn.MarineClinic.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seaman")
public class SeamanController {
    @GetMapping
    public String seamanPage(){
        return "Seaman";
    }
}
