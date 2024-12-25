package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.Enum.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoExam;
import cn.edu.zjut.examsystem.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseMessage<Boolean> add(@RequestBody PoExam exam)
    {
        return new ResponseMessage<>(Code.SUCCESS,"添加成功",examService.add(exam));
    }

    @GetMapping("/id/{id}")
    public ResponseMessage<PoExam> findById(@PathVariable int id)
    {
        PoExam exam = examService.findById(id);
        if(exam == null) return ResponseMessage.fail("未查询到有效目标",null);
        else return ResponseMessage.success("id查询成功",exam);
    }

    @GetMapping("/inputData/{inputData}")
    public ResponseMessage<List<PoExam>> findAllByStr(@PathVariable String inputData)
    {
        List<PoExam> exams = examService.findAllByStr(inputData);
        if(exams == null || exams.isEmpty()) return new ResponseMessage<>(Code.FAIL,"未查询到数据",null);
        return new ResponseMessage<>(Code.SUCCESS,"查询成功",exams);
    }

    @GetMapping
    public ResponseMessage<List<PoExam>> findAll()
    {
        List<PoExam> exams = examService.findAll();
        if(exams == null || exams.isEmpty()) return new ResponseMessage<>(Code.FAIL,"未查询到数据",null);
        return new ResponseMessage<>(Code.SUCCESS,"查询成功",exams);
    }

    @PutMapping
    public ResponseMessage<Boolean> alter(@RequestBody PoExam exam)
    {
        return new ResponseMessage<>(Code.SUCCESS,"修改成功",examService.alter(exam));
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Boolean> deleteById(@PathVariable int id)
    {
        if(examService.deleteById(id)) return new ResponseMessage<>(Code.SUCCESS,"删除成功",true);
        else return new ResponseMessage<>(Code.FAIL,"删除失败",false);
    }
}
