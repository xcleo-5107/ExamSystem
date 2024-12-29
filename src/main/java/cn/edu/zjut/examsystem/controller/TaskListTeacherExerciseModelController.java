package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoTaskListTeacherAnswerSheetModel;
import cn.edu.zjut.examsystem.po.PoTaskListTeacherExerciseModel;
import cn.edu.zjut.examsystem.service.TaskListTeacherExerciseModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TaskListTeacherExerciseModel")
public class TaskListTeacherExerciseModelController {

    @Autowired
    private TaskListTeacherExerciseModelService tlteService;


    @GetMapping("/{teacherNum}")
    public ResponseMessage<List<PoTaskListTeacherExerciseModel>> findAllByTeacherNum(@PathVariable int teacherNum)
    {
        List<PoTaskListTeacherExerciseModel> tasks = tlteService.findAllByTeacherNum(teacherNum);
        if(tasks == null || tasks.isEmpty()) return ResponseMessage.fail("未查询到有效目标",null);
        else return ResponseMessage.success("查询成功",tasks);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Boolean> deleteById(@PathVariable int id)
    {
        if(tlteService.deleteById(id)) return ResponseMessage.success("任务提交",true);
        else return ResponseMessage.fail("目标不存在",false);
    }
}
