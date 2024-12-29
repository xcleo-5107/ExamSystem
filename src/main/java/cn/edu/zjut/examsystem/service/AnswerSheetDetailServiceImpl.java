package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoAnswerSheetDetail;

import java.util.List;

public interface AnswerSheetDetailServiceImpl {
    int saveScore(int detailId,int score);

    List<PoAnswerSheetDetail> findAllByModuleExerciseIdAndExamSchemeNum(int moduleExerciseId,int examSchemeNum);
}
