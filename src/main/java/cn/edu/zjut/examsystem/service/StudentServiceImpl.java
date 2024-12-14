package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoStudent;

import java.util.Set;

public interface StudentServiceImpl {
    PoStudent findByStudentId(int id);
    Set<PoStudent> findAllByStudentName(String name);
    PoStudent addStudent(PoStudent student);
}
