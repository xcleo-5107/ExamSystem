package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.TaskListTeacherAnswerSheetModelDao;
import cn.edu.zjut.examsystem.po.PoTaskListTeacherAnswerSheetModel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskListTeacherAnswerSheetModelService implements TaskListTeacherAnswerSheetModelServiceImpl{
    @Autowired
    private TaskListTeacherAnswerSheetModelDao tltasDao;


    @Override
    @Transactional
    public boolean deleteByid(int id) {
        if(!tltasDao.existsById(id)) return false;

        tltasDao.deleteById(id);
        return true;
    }

    @Override
    public List<PoTaskListTeacherAnswerSheetModel> findAllByTeacherNum(int teacherNum) {
        return tltasDao.findAllByTeacherNum(teacherNum);
    }
}
