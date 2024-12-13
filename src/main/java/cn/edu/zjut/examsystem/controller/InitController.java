package cn.edu.zjut.examsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController {
    @RequestMapping(value = "/hello")
    public String speak(Model model) {
        return "index";
    }

}
