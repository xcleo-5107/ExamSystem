package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.Enum.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public ResponseMessage<List<PoClazz>> findAll()
    {
        List<PoClazz> clazzes = clazzService.findAll();
        if(clazzes == null || clazzes.isEmpty()) return new ResponseMessage<>(Code.FAIL,"为查询到有效资源",null);
        else return new ResponseMessage<>(Code.SUCCESS,"查询成功",clazzes);
    }

    @GetMapping("/classId/{classId}")
    public ResponseMessage<PoClazz> findByClassId(@PathVariable int classId)
    {
        PoClazz clazz = clazzService.findById(classId);
        if(clazz == null) return new ResponseMessage<>(Code.FAIL,"为查询到有效资源",null);
        else return new ResponseMessage<>(Code.SUCCESS,"查询成功",clazz);
    }

    @GetMapping("/classesStr/{classesStr}")
    public ResponseMessage<List<PoClazz>> findAllByClazzesStr(@PathVariable String classesStr)
    {
        List<PoClazz> clazzes = clazzService.findAllByClazzesStr(classesStr);
        if(clazzes == null || clazzes.isEmpty()) return new ResponseMessage<>(Code.FAIL,"为查询到有效资源",null);
        else return new ResponseMessage<>(Code.SUCCESS,"查询成功",clazzes);
    }

    @GetMapping("/examSchemeNum/{examSchemeNum}")
    public ResponseMessage<List<PoClazz>> findByExamSchemeNum(@PathVariable int examSchemeNum)
    {
        List<PoClazz> clazzes = clazzService.findAllByExamSchemeNum(examSchemeNum);
        if(clazzes == null || clazzes.isEmpty()) return new ResponseMessage<>(Code.FAIL,"未查询到有效资源",null);
        else return new ResponseMessage<>(Code.SUCCESS,"查询成功",clazzes);
    }

    @GetMapping("/studentId/{studentId}")
    public ResponseMessage<List<PoClazz>> findAllByStudentId(@PathVariable int studentId)
    {
        List<PoClazz> clazzes = clazzService.findAllByStudentId(studentId);
        if(clazzes == null || clazzes.isEmpty()) return ResponseMessage.fail("未查询到有效目标",null);
        return ResponseMessage.success("查询成功",clazzes);
    }

    @DeleteMapping("/classNum/{classNum}/studentId/{studentId}")
    public ResponseMessage<Boolean> deleteStudentByStudentId(@PathVariable int classNum,@PathVariable int studentId)
    {
        boolean res = clazzService.deleteStudentByStudentId(classNum,studentId);
        if(!res) return new ResponseMessage<>(Code.FAIL,"修改失败,目标不存在或在此期间已被更改",false);
        else return new ResponseMessage<>(Code.SUCCESS,"修改成功",true);
    }

}
