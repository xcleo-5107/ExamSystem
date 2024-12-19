package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoExamScheme;
import cn.edu.zjut.examsystem.service.ExamSchemeService;
import cn.edu.zjut.examsystem.service.ExamSchemeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/examScheme")
public class ExamSchemeController {
    @Autowired
    private ExamSchemeService examSchemeService;

    @GetMapping("/inputData/{inputData}")
    public ResponseMessage<List<PoExamScheme>> findAllByInputData(@PathVariable String inputData)
    {
        List<PoExamScheme> examSchemes = examSchemeService.findAllByStr(inputData);

        if(examSchemes == null || examSchemes.isEmpty()) return new ResponseMessage<>(Code.FAIL,"未查询到有效数据",null);
        else return new ResponseMessage<>(Code.SUCCESS,"查询成功",examSchemes);
    }

    @PutMapping
    public ResponseMessage<PoExamScheme> alter(@RequestBody PoExamScheme examScheme)
    {
        return new ResponseMessage<>(Code.SUCCESS,"修改成功",examSchemeService.alter(examScheme));
    }
}
