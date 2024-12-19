package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping("/classesStr/{classesStr}")
    public ResponseMessage<List<PoClazz>> findAllByClazzesStr(@PathVariable String classesStr)
    {
        List<PoClazz> clazzes = clazzService.findAllByClazzesStr(classesStr);
        if(clazzes == null || clazzes.isEmpty()) return new ResponseMessage<>(Code.FAIL,"为查询到有效资源",null);
        else return new ResponseMessage<>(Code.SUCCESS,"查询成功",clazzes);
    }
}
