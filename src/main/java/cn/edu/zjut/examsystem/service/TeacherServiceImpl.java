package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoTeacher;

public interface TeacherServiceImpl {
    PoTeacher findByTeacherNum(int num);
    PoTeacher addTeacher(PoTeacher teacher);
}
