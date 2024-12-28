package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.TaskListTeacherExerciseModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
