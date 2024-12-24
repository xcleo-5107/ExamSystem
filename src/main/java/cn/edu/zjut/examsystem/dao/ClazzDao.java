package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoClazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClazzDao extends JpaRepository<PoClazz,Integer> {

    List<PoClazz> findAllByStudents_StudentId(int studentId);
}
