package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoExam;
import cn.edu.zjut.examsystem.po.PoExamModule;
import cn.edu.zjut.examsystem.po.PoModuleExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ExamDao extends JpaRepository<PoExam, Integer> {
    //写复杂的sql只需要查相应Po类引用的num及其自身的属性即可.真正读到时会自己补全的
    @Query(value = "select e.exam_num," +
            "e.is_released,"+
            "c.course_num," +
            "t.teacher_num " +
            "from exam e " +
            "JOIN exam_module AS em ON e.exam_num = em.exam_num " +
            "JOIN module_exercise AS me ON me.module_num = em.module_num " +
            "JOIN exercise AS ex ON ex.exercise_num = me.exercise_num " +
            "JOIN course AS c ON c.course_num = e.course_num " +
            "JOIN teacher AS t ON e.teacher_num = t.teacher_num " +
            "WHERE c.course_name LIKE ?1 " +
            "OR t.teacher_name LIKE ?1 " +
            "OR ex.exercise_answer LIKE ?1 " +
            "OR ex.exercise_info LIKE ?1 " +
            "OR ex.exercise_problem LIKE ?1",nativeQuery = true)
    List<PoExam> findAllByStr(String str);
}
