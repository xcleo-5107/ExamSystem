package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoAnswerSheetDetail;

import java.util.List;

public interface AnswerSheetDetailServiceImpl {
    int saveScore(int detailId,int score);

    List<PoAnswerSheetDetail> findAllBySchemeNumAndExerciseType(int schemeNum,int exerciseTypeNum);
    List<PoAnswerSheetDetail> findAllByModuleExerciseId(int moduleExerciseId);
}
