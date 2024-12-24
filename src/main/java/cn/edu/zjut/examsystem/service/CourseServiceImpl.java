package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoCourse;

import java.util.List;

public interface CourseServiceImpl {
    List<PoCourse> findAll();
    List<PoCourse> findAllByStr(String str);

    Boolean add(PoCourse course);
    Boolean alter(PoCourse course);

    Boolean deleteById(int id);

}
