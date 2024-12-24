package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoStudent;

import java.util.List;
import java.util.Set;

public interface StudentServiceImpl {
    PoStudent findByStudentId(int id);
    Set<PoStudent> findAllByStudentName(String name);
    List<PoStudent> findAll();
    List<PoStudent> findAllByStr(String str);
    Boolean add(PoStudent student);
    Boolean alter(PoStudent student);
    Boolean deleteById(int id);
}
