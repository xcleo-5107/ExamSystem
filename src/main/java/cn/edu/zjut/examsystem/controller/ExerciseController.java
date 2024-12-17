package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoExercise;
import cn.edu.zjut.examsystem.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.LinkOption;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @PostMapping
    public ResponseMessage<Boolean> add(@RequestBody PoExercise exercise)
    {
        return new ResponseMessage<>(Code.SUCCESS,"添加成功", exerciseService.add(exercise));
    }
    @GetMapping
    public ResponseMessage<List<PoExercise>> findAll()
    {
        List<PoExercise> exercises = exerciseService.findAll();
        if(exercises == null||exercises.isEmpty()) return new ResponseMessage<>(Code.FAIL,"未查询到数据",null);
        else return new ResponseMessage<>(Code.FAIL,"查询成功",exercises);
    }
    @GetMapping("/id/{id}")
    public ResponseMessage<PoExercise> findById(@PathVariable int id)
    {
        PoExercise exercise = exerciseService.findById(id);
        if(exercise == null) return new ResponseMessage<>(Code.FAIL,"未查询到数据",null);
        else return new ResponseMessage<>(Code.SUCCESS,"id查询题目成功",exercise);
    }

    @GetMapping("/inputData/{inputData}")
    public ResponseMessage<List<PoExercise>> findAllByStr(@PathVariable String inputData)
    {
        List<PoExercise> exercises = exerciseService.findAllByStr(inputData);
        if(exercises == null || exercises.isEmpty()) return new ResponseMessage<>(Code.FAIL,"未查询到结果",null);
        else return new ResponseMessage<>(Code.SUCCESS,"模糊查询成功",exercises);
    }

    @PutMapping
    public ResponseMessage<PoExercise> alterExercise(@Validated @RequestBody PoExercise exercise)
    {
        return new ResponseMessage<>(Code.SUCCESS,"修改题目成功",exerciseService.alter(exercise));
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Boolean> deleteById(@PathVariable int id)
    {
        if(exerciseService.deleteById(id)) return new ResponseMessage<>(Code.SUCCESS,"删除成功",true);
        else return new ResponseMessage<>(Code.FAIL,"未查询到目标",false);
    }
}
