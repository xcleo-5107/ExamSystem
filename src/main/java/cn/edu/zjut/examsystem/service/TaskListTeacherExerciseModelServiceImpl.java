package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoTaskListTeacherExerciseModel;

import java.util.List;

public interface TaskListTeacherExerciseModelServiceImpl {
    boolean deleteById(int id);

    List<PoTaskListTeacherExerciseModel> findAllByTeacherNum(int teacherNum);
}
