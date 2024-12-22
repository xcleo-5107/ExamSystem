package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoAnswerSheet;

import java.util.List;

public interface AnswerSheetServiceImpl {
    List<PoAnswerSheet> findAllByExamSchemeNum(int schemeNum);
    PoAnswerSheet findBySheetNum(int num);
}
