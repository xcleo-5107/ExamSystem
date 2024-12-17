package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ExerciseDao extends JpaRepository<PoExercise, Integer> {
    List<PoExercise> findAllByCourse_CourseNum(int courseNum);

    List<PoExercise> findAllByExerciseInfoContainingOrExerciseProblemContainingOrExerciseAnswerContainingOrExerciseType_TypeNameContainingOrCourse_CourseName(String str1,String str2,String str3,String str4,String str5);
}
