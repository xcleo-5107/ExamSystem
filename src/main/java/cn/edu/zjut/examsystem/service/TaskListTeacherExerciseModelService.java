package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.TaskListTeacherExerciseModelDao;
import cn.edu.zjut.examsystem.po.PoTaskListTeacherExerciseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskListTeacherExerciseModelService implements TaskListTeacherExerciseModelServiceImpl{
    @Autowired
    private TaskListTeacherExerciseModelDao tlteDao;


    @Override
    @Transactional
    public boolean deleteById(int id) {
        if(!tlteDao.existsById(id)) return false;

        tlteDao.deleteById(id);
        return true;
    }

    @Override
    public List<PoTaskListTeacherExerciseModel> findAllByTeacherNum(int teacherNum) {
        return tlteDao.findAllByTeacherNum(teacherNum);
    }
}
