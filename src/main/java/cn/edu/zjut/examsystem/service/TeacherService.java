package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.TeacherDao;
import cn.edu.zjut.examsystem.po.PoTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService implements TeacherServiceImpl{


    @Autowired
    private TeacherDao teacherDao;

    @Override
    public PoTeacher findByTeacherNum(int num) {
        return teacherDao.findById(num).orElse(null);
    }

    @Transactional
    @Override
    public Boolean addTeacher(PoTeacher teacher) {
        teacherDao.save(teacher);
        return true;
    }
}
