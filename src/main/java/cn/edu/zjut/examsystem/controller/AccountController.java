package cn.edu.zjut.examsystem.controller;


import cn.edu.zjut.examsystem.po.PoLeaderAccount;
import cn.edu.zjut.examsystem.po.PoStudentAccount;
import cn.edu.zjut.examsystem.po.PoTeacherAccount;
import cn.edu.zjut.examsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/loginAsStudent")
    public String loginAsStudent(@RequestParam String username, @RequestParam String password, Model model)
    {
        PoStudentAccount account = accountService.loginAsStudent(username,password);
        if(account == null) return "";
        else
        {
            model.addAttribute("account",account);
            return "";
        }
    }

    @GetMapping("/loginAsTeacher")
    public String loginAsTeacher(@RequestParam String username, @RequestParam String password, Model model)
    {
        PoTeacherAccount account = accountService.loginAsTeacher(username,password);
        if(account == null) return "";
        else
        {
            model.addAttribute("account",account);
            return "";
        }
    }

    @GetMapping("/loginAsLeader")
    public String loginAsLeader(@RequestParam String username, @RequestParam String password, Model model)
    {
        PoLeaderAccount account = accountService.loginAsLeader(username,password);
        if(account == null) return "";
        else
        {
            model.addAttribute("account",account);
            return "";
        }
    }
}
