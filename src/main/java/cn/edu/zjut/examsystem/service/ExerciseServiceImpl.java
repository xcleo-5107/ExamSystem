package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoExercise;

import java.util.List;
import java.util.Set;

public interface ExerciseServiceImpl {
    List<PoExercise> findAllByCourseNum(int num);
    List<PoExercise> findAll();
    List<PoExercise> findAllByStr(String str);
    PoExercise findById(int num);

    Boolean alter(PoExercise exercise);
    Boolean add(PoExercise exercise);
    Boolean deleteById(int num);
}
