package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoExam;
import cn.edu.zjut.examsystem.po.PoExamModule;
import cn.edu.zjut.examsystem.po.PoModuleExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ExamDao extends JpaRepository<PoExam, Integer> {
}
