package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoExamScheme;
import cn.edu.zjut.examsystem.po.PoExamType;

import java.util.List;

public interface ExamSchemeServiceImpl {
    List<PoExamScheme> findAllByStr(String str);
    List<PoExamScheme> findAll();
    List<PoExamScheme> findAllByStudentId(int studentId);
    Boolean alter(PoExamScheme examScheme);
    Boolean add(PoExamScheme examScheme);

//    void autoUpdateScheme();//自动更新考试安排状态
//    void autoUpdateCredit();//自动添加学分
}
