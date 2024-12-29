package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoTaskListTeacherAnswerSheetModel;
import cn.edu.zjut.examsystem.service.TaskListTeacherAnswerSheetModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TaskListTeacherAnswerSheetModel")
public class TaskListTeacherAnswerSheetModelController {
    @Autowired
    private TaskListTeacherAnswerSheetModelService tltasService;

    @GetMapping("/{teacherNum}")
    public ResponseMessage<List<PoTaskListTeacherAnswerSheetModel>> findAllByTeacherNum(@PathVariable int teacherNum)
    {
        List<PoTaskListTeacherAnswerSheetModel> tasks = tltasService.findAllByTeacherNum(teacherNum);
        if(tasks == null || tasks.isEmpty()) return ResponseMessage.fail("未查询到有效目标",null);
        else return ResponseMessage.success("查询成功",tasks);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Boolean> deleteById(@PathVariable int id)
    {
        if(tltasService.deleteByid(id)) return ResponseMessage.success("任务提交",true);
        else return ResponseMessage.fail("目标不存在",false);
    }



}
