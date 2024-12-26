package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.Enum.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoCourse;
import cn.edu.zjut.examsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseMessage<Boolean> add(@RequestBody PoCourse course)
    {
        return new ResponseMessage<>(Code.SUCCESS,"添加成功",courseService.add(course));
    }

    @GetMapping
    public ResponseMessage<List<PoCourse>> findAll()
    {
        List<PoCourse> courses = courseService.findAll();
        if(courses == null || courses.isEmpty()) return ResponseMessage.fail("未查询到有效目标",null);
        else return ResponseMessage.success("查询成功",courses);
    }

    @GetMapping("/inputData/{inputData}")
    public ResponseMessage<List<PoCourse>> findAllByStr(@PathVariable String inputData)
    {
        List<PoCourse> courses = courseService.findAllByStr(inputData);
        if(courses == null || courses.isEmpty()) return ResponseMessage.fail("未查询到有效目标",null);
        else return ResponseMessage.success("查询成功",courses);
    }

    @GetMapping("/studentId/{studentId}")
    public ResponseMessage<List<PoCourse>> findAllByStudentId(@PathVariable int studentId)
    {
        List<PoCourse> courses = courseService.findByStudentId(studentId);
        if(courses == null || courses.isEmpty()) return ResponseMessage.fail("未查询到有效数据",null);
        else return ResponseMessage.success("查询成功",courses);
    }

    @PutMapping
    public ResponseMessage<Boolean> alter(@RequestBody PoCourse course)
    {
        return new ResponseMessage<>(Code.SUCCESS,"修改成功",courseService.alter(course));
    }

    @DeleteMapping("/{courseNum}")
    public ResponseMessage<Boolean> deleteById(@PathVariable int courseNum)
    {
        if(courseService.deleteById(courseNum))return new ResponseMessage<>(Code.SUCCESS,"删除成功",true);

        return new ResponseMessage<>(Code.FAIL,"修改失败",false);
    }
}
