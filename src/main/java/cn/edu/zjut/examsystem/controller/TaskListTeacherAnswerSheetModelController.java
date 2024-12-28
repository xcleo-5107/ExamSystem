package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.service.TaskListTeacherAnswerSheetModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TaskListTeacherAnswerSheetModel")
public class TaskListTeacherAnswerSheetModelController {
    @Autowired
    private TaskListTeacherAnswerSheetModelService tltasService;

    @DeleteMapping("/{id}")
    public ResponseMessage<Boolean> deleteById(@PathVariable int id)
    {
        if(tltasService.deleteByid(id)) return ResponseMessage.success("任务提交",true);
        else return ResponseMessage.fail("目标不存在",false);
    }

}
