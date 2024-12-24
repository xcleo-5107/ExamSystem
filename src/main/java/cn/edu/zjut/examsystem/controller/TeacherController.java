package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoStudent;
import cn.edu.zjut.examsystem.po.PoTeacher;
import cn.edu.zjut.examsystem.service.StudentService;
import cn.edu.zjut.examsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    public TeacherService teacherService;

    //POST方式一般用于添加
    //@RequestBody用于将传入的json响应接收
    @PostMapping
    public ResponseMessage<Boolean> add(@Validated @RequestBody PoTeacher teacher)
    {
        return ResponseMessage.success("添加新教师数据成功",teacherService.add(teacher));
    }


    @GetMapping("/id/{id}")
    public ResponseMessage<PoTeacher> findById(@RequestParam int id)
    {
        return ResponseMessage.success("编号查询教师成功",teacherService.findByTeacherNum(id));
    }

    @GetMapping
    public  ResponseMessage<List<PoTeacher>> findAll()
    {
        return ResponseMessage.success("查询成功",teacherService.findAll());
    }

    @GetMapping("/inputData/{inputData}")
    public ResponseMessage<List<PoTeacher>> findAllByStr(@PathVariable String inputData)
    {
        return ResponseMessage.success("查询成功",teacherService.findAllByStr(inputData));
    }

    @PutMapping
    public ResponseMessage<Boolean> alter(@RequestBody PoTeacher teacher)
    {
        if(teacherService.alter(teacher)) return ResponseMessage.success("修改成功",true);
        else return ResponseMessage.fail("修改失败",false);
    }

    @DeleteMapping("/{teacherNum}")
    public ResponseMessage<Boolean> deleteById(@PathVariable int teacherNum)
    {
        if(teacherService.deleteById(teacherNum)) return ResponseMessage.success("删除成功",true);
        else return ResponseMessage.fail("未查询到目标",false);
    }

}
