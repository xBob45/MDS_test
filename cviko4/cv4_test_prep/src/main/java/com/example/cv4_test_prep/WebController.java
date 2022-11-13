package com.example.cv4_test_prep;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {

    @GetMapping("static")
    public String static_page(){
        return "staticpage";}

    @RequestMapping("dynamic")
    public String dynamicpage(Model model){
        model.addAttribute("first_name" ,"Fojta");
        model.addAttribute("last_name", "fon Wiena");
        model.addAttribute("nationality", "Austrian");
        return "dynamicpage";
    }


    //-----------------------------COMPLEX EXAMPLE------------------------------

    @RequestMapping("bob")
    public String bob(Model model){
        model.addAttribute("name" ,"Bob");
        return "example";
    }

    @RequestMapping("alice")
    public String alice(Model model){
        model.addAttribute("name", "Alice");
        return "example";
    }






}
