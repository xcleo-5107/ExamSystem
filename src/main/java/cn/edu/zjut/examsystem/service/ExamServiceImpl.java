package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dto.AutoCreateExamDto;
import cn.edu.zjut.examsystem.po.PoExam;

import java.util.List;

public interface ExamServiceImpl {
    PoExam findById(int id);
    Boolean alter(PoExam exam);
    Boolean add(PoExam exam);
    Boolean deleteById(int id);
    List<PoExam> findAllByStr(String str);
    List<PoExam> findAll();

    PoExam autoCreateExam(AutoCreateExamDto autoCreateExamDto);
}
