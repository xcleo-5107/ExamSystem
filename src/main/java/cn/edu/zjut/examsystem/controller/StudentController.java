package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoStudent;
import cn.edu.zjut.examsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

//用于指定将方法返回的对象转换为 JSON 或 XML 格式的响应体
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    public StudentService studentService;

    //POST方式一般用于添加
    //@RequestBody用于将传入的json响应接收
    @PostMapping("/addStudent")
    public ResponseMessage<PoStudent> addStudent(@Validated @RequestBody PoStudent student)
    {
        return ResponseMessage.success("添加新学生数据成功",studentService.addStudent(student));
    }


    @GetMapping("/findByStudentId")
    public ResponseMessage<PoStudent> findByStudentId(@RequestParam int id)
    {
        return ResponseMessage.success("id查询学生成功",studentService.findByStudentId(id));
    }

    @GetMapping("/findAllByStudentName")
    public ResponseMessage<Set<PoStudent>> findAllByStudentName(@RequestParam String name)
    {
        return ResponseMessage.success("名字查询学生成功",studentService.findAllByStudentName(name));
    }
}
