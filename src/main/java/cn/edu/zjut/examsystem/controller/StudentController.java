package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.dao.StudentDao;
import cn.edu.zjut.examsystem.po.PoStudent;
import cn.edu.zjut.examsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//用于指定将方法返回的对象转换为 JSON 或 XML 格式的响应体
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    public StudentService studentService;

    //POST方式一般用于添加
    //@RequestBody用于将传入的json响应接收
    @PostMapping
    public ResponseMessage<Boolean> add(@Validated @RequestBody PoStudent student)
    {
        return ResponseMessage.success("添加新学生数据成功",studentService.add(student));
    }

    @GetMapping
    public ResponseMessage<List<PoStudent>> findAll()
    {
        List<PoStudent> students = studentService.findAll();
        if(students == null || students.isEmpty()) return ResponseMessage.fail("未查询到有效目标",null);
        else return ResponseMessage.success("查询成功",students);
    }

    @GetMapping("/{inputDate}")
    public ResponseMessage<List<PoStudent>> findAllByStr(@PathVariable String inputDate)
    {
        List<PoStudent> students = studentService.findAllByStr(inputDate);
        if(students == null || students.isEmpty()) return ResponseMessage.fail("未查询到有效目标",null);
        else return ResponseMessage.success("查询成功",students);
    }


    @GetMapping("/id/{id}")
    public ResponseMessage<PoStudent> findByStudentId(@PathVariable(value = "id") int id)
    {
        PoStudent student = studentService.findByStudentId(id);

        if(student == null) return ResponseMessage.fail("未查询到有效目标",null);
        return ResponseMessage.success("id查询学生成功",student);
    }

    @GetMapping("/name/{name}")
    public ResponseMessage<List<PoStudent>> findAllByStudentName(@PathVariable("name") String name)
    {
        List<PoStudent> students = studentService.findAllByStudentName(name);
        if(students == null || students.isEmpty()) return ResponseMessage.fail("未查询到有效目标",null);
        else return ResponseMessage.success("查询成功",students);
    }

    @PutMapping
    public ResponseMessage<Boolean> alter(@RequestBody PoStudent student)
    {
        return ResponseMessage.success("修改成功", studentService.alter(student));
    }

    @DeleteMapping("/{studentId}")
    public ResponseMessage<Boolean> deleteById(@PathVariable int studentId)
    {
        if(studentService.deleteById(studentId)) return ResponseMessage.success("删除成功",true);
        else return ResponseMessage.fail("删除失败",false);
    }
}
