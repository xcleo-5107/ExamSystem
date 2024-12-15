package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoStudent;
import cn.edu.zjut.examsystem.po.PoTeacher;
import cn.edu.zjut.examsystem.service.StudentService;
import cn.edu.zjut.examsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    public TeacherService teacherService;

    //POST方式一般用于添加
    //@RequestBody用于将传入的json响应接收
    @PostMapping("/addTeacher")
    public ResponseMessage<PoTeacher> addTeacher(@Validated @RequestBody PoTeacher teacher)
    {
        return ResponseMessage.success("添加新教师数据成功",teacherService.addTeacher(teacher));
    }


    @GetMapping("/findByTeacherNum")
    public ResponseMessage<PoTeacher> findByTeacherNum(@RequestParam int num)
    {
        return ResponseMessage.success("编号查询教师成功",teacherService.findByTeacherNum(num));
    }
}
