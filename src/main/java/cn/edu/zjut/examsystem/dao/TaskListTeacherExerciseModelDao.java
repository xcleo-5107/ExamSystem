package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoTaskListTeacherExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskListTeacherExerciseModelDao extends JpaRepository<PoTaskListTeacherExerciseModel,Integer> {

    @Query(value = "select count(tltem.id) " +
            "from task_list_teacher_exercise_model as tltem " +
            "join module_exercise as me on me.module_exercise_id = tltem.model_exercise_id " +
            "join exam_module as em on em.module_num = me.module_num " +
            "join exam as e on e.exam_num = em.module_num " +
            "join exam_scheme as es on es.exam_num = e.exam_num " +
            "where es.scheme_num = ?1 ",nativeQuery = true)
    Object countAllByExamSchemeNum(int schemeNum);
}
