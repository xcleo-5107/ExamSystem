package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoTeacher;

import java.util.List;

public interface TeacherServiceImpl {
    PoTeacher findByTeacherNum(int num);
    Boolean add(PoTeacher teacher);
    Boolean alter(PoTeacher teacher);
    Boolean deleteById(int id);

    List<PoTeacher> findAll();
    List<PoTeacher> findAllByStr(String str);
}
