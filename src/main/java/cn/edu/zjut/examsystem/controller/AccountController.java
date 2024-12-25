package cn.edu.zjut.examsystem.controller;


import cn.edu.zjut.examsystem.Enum.Code;
import cn.edu.zjut.examsystem.Enum.LoginStatus;
import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.dto.AccountDto;
import cn.edu.zjut.examsystem.po.PoLeaderAccount;
import cn.edu.zjut.examsystem.po.PoStudentAccount;
import cn.edu.zjut.examsystem.po.PoTeacherAccount;
import cn.edu.zjut.examsystem.service.AccountService;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/studentLogin")
    public ResponseMessage<Integer> studentLogin(@Validated @RequestBody AccountDto accountDto)
    {
        int stuId = accountService.loginAsStudent(accountDto);
        if(stuId == LoginStatus.UserNameOrPasswordFail.getValue()) return new ResponseMessage<>(Code.FAIL,"用户密码错误",null);
        else if(stuId == LoginStatus.AccountHaveNoEntity.getValue()) return new ResponseMessage<>(Code.FAIL,"该账号还未绑定学生",null);
        else if(stuId == LoginStatus.EntityNotFound.getValue()) return new ResponseMessage<>(Code.FAIL,"该账号绑定的学生不存在",null);
        else return new ResponseMessage<>(Code.SUCCESS,"登录成功",stuId);
    }

    @PostMapping("/teacherLogin")
    public ResponseMessage<Integer> teacherLogin(@Validated @RequestBody AccountDto accountDto)
    {
        int teacherNum = accountService.loginAsTeacher(accountDto);
        if(teacherNum == LoginStatus.UserNameOrPasswordFail.getValue()) return new ResponseMessage<>(Code.FAIL,"用户密码错误",null);
        else if(teacherNum == LoginStatus.AccountHaveNoEntity.getValue()) return new ResponseMessage<>(Code.FAIL,"该账号还未绑定教师",null);
        else if(teacherNum == LoginStatus.EntityNotFound.getValue()) return new ResponseMessage<>(Code.FAIL,"该账号绑定的教师不存在",null);
        else return new ResponseMessage<>(Code.SUCCESS,"登录成功",teacherNum);
    }

    @PostMapping("/leaderLogin")
    public ResponseMessage<Integer> leaderLogin(@Validated @RequestBody AccountDto accountDto)
    {
        int loginStatus = accountService.loginAsLeader(accountDto);
        if(loginStatus == LoginStatus.UserNameOrPasswordFail.getValue()) return new ResponseMessage<>(Code.FAIL,"用户密码错误",null);
        else return new ResponseMessage<>(Code.SUCCESS,"登录成功",null);
    }
}
