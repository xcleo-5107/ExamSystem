package cn.edu.zjut.examsystem.controller;

import cn.edu.zjut.examsystem.Enum.Code;
import cn.edu.zjut.examsystem.ResponseMessage;
import cn.edu.zjut.examsystem.po.PoAnswerSheet;
import cn.edu.zjut.examsystem.po.PoAnswerSheetDetail;
import cn.edu.zjut.examsystem.service.AnswerSheetDetailService;
import cn.edu.zjut.examsystem.service.AnswerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AnswerSheet")
public class AnswerSheetController {
    @Autowired
    private AnswerSheetService answerSheetService;
    @Autowired
    private AnswerSheetDetailService answerSheetDetailService;


    @PostMapping
    public ResponseMessage<Boolean> add(@RequestBody PoAnswerSheet answerSheet)
    {
        if(answerSheetService.add(answerSheet)) return ResponseMessage.success("保存成功",true);
        else return ResponseMessage.success("保存失败",false);
    }

    @GetMapping("/examScheme/{examScheme}")
    public ResponseMessage<List<PoAnswerSheet>> findAllByExamScheme(@PathVariable int examScheme)
    {
        List<PoAnswerSheet> answerSheets = answerSheetService.findAllByExamSchemeNum(examScheme);

        if(answerSheets == null || answerSheets.isEmpty()) return new ResponseMessage<>(Code.FAIL,"未查询到有效目标",null);
        else return new ResponseMessage<>(Code.SUCCESS,"查询成功",answerSheets);
    }

    @GetMapping("/examSchemeNum/{examSchemeNum}/studentId/{studentId}")
    public ResponseMessage<PoAnswerSheet> findByStudentIdAndSchemeNum(@PathVariable int studentId,@PathVariable int examSchemeNum)
    {
        PoAnswerSheet answerSheet = answerSheetService.findByStudentIdAndSchemeNum(studentId,examSchemeNum);
        if(answerSheet == null) return  ResponseMessage.fail("未查询到有效目标",null);
        else return ResponseMessage.success("查询成功",answerSheet);
    }

    @GetMapping("/sheetNum/{sheetNum}")
    public ResponseMessage<PoAnswerSheet> findBySheetNum(@PathVariable int sheetNum)
    {
        PoAnswerSheet answerSheet = answerSheetService.findBySheetNum(sheetNum);

        if(answerSheet == null) return new ResponseMessage<>(Code.FAIL,"未查询到有效目标",null);
        else return new ResponseMessage<>(Code.SUCCESS,"查询成功",answerSheet);
    }

    @GetMapping("/moduleExerciseId/{moduleExerciseId}/examSchemeNum/{examSchemeNum}")
    public ResponseMessage<List<PoAnswerSheetDetail>> findAllByModuleExerciseIdAndExamSchemeNum(@PathVariable int moduleExerciseId,@PathVariable int examSchemeNum)
    {
        List<PoAnswerSheetDetail> answerSheetDetails = answerSheetDetailService.findAllByModuleExerciseIdAndExamSchemeNum(moduleExerciseId,examSchemeNum);
        if(answerSheetDetails == null || answerSheetDetails.isEmpty()) return ResponseMessage.fail("未查询到有效数据",null);
        else return ResponseMessage.success("查询成功",answerSheetDetails);
    }

    @PutMapping("/detailId/{detailId}/score/{score}")
    public ResponseMessage<Integer> saveScore(@PathVariable int detailId,@PathVariable int score)
    {
        int res = answerSheetDetailService.saveScore(detailId, score);
        if(res == 0) return new ResponseMessage<>(Code.FAIL,"该答题表详情页不存在",0);
        else return new ResponseMessage<>(Code.FAIL,"保存成功",res);
    }
}
