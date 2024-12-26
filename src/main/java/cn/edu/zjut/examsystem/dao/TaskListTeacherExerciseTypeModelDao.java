package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoTaskListTeacherExerciseTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListTeacherExerciseTypeModelDao extends JpaRepository<PoTaskListTeacherExerciseTypeModel,Integer> {
}
