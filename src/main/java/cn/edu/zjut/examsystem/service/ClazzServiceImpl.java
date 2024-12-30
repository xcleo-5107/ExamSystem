package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoClazz;

import java.util.List;

public interface ClazzServiceImpl {
    //根据一个被空格分隔的字符串查询班级
    List<PoClazz> findAllByClazzesStr(String targets);

    List<PoClazz> findAllByExamSchemeNum(int schemeNum);

    List<PoClazz> findAll();
    List<PoClazz> findAllByStudentId(int studentId);

    PoClazz findById(int id);

    boolean deleteStudentByStudentId(int clazzNum,int StudentId);
    boolean deleteTeacherByTeacherNum(int clazzNum,int teacherNum);
    boolean add(PoClazz clazz);
    boolean addStudent(int clazzNum,int studentId);
    boolean addTeacher(int clazzNum,int teacherNum);
}
