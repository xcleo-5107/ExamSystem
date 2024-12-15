package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.TeacherDao;
import cn.edu.zjut.examsystem.po.PoTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements TeacherServiceImpl{
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public PoTeacher findByTeacherNum(int num) {
        return teacherDao.findById(num).orElse(null);
    }

    @Override
    public PoTeacher addTeacher(PoTeacher teacher) {
        return teacherDao.save(teacher);
    }
}
