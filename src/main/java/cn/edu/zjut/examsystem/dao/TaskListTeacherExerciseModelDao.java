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
            "where tltem.exam_scheme_num = ?1 ",nativeQuery = true)
    Object countAllByExamSchemeNum(int schemeNum);

    List<PoTaskListTeacherExerciseModel> findAllByTeacherNum(int teacherNum);
}
