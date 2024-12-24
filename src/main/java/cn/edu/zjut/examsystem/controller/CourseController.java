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
        return new ResponseMessage<>(Code.SUCCESS,"查询成功",courseService.findAll());
    }

    @GetMapping("/{inputData}")
    public ResponseMessage<List<PoCourse>> findAllByStr(@PathVariable String inputData)
    {
        return new ResponseMessage<>(Code.SUCCESS,"查询成功",courseService.findAllByStr(inputData));
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
