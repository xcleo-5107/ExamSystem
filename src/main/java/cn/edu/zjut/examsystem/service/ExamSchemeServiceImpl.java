package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoExamScheme;
import cn.edu.zjut.examsystem.po.PoExamType;

import java.util.List;

public interface ExamSchemeServiceImpl {
    List<PoExamScheme> findAllByStr(String str);
    PoExamScheme alter(PoExamScheme examScheme);
}
