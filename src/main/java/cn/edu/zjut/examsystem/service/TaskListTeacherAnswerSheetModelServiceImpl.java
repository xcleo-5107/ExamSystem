package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoTaskListTeacherAnswerSheetModel;

import java.util.List;

public interface TaskListTeacherAnswerSheetModelServiceImpl {
    boolean deleteByid(int id);

    List<PoTaskListTeacherAnswerSheetModel> findAllByTeacherNum(int teacherNum);
}
