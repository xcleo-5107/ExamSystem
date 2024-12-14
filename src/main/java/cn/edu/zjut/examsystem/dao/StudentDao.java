package cn.edu.zjut.examsystem.dao;


import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.po.PoStudent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentDao extends CrudRepository<PoStudent, Integer> {
    Set<PoStudent> findAllByStudentName(String name);
}
