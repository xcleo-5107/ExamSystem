package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.TeacherDao;
import cn.edu.zjut.examsystem.po.PoTeacher;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService implements TeacherServiceImpl{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public PoTeacher findByTeacherNum(int num) {
        return teacherDao.findById(num).orElse(null);
    }

    @Transactional
    @Override
    public Boolean add(PoTeacher teacher) {
        entityManager.persist(teacher);
        return true;
    }

    @Transactional
    @Override
    public Boolean alter(PoTeacher teacher) {
        if(!teacherDao.existsById(teacher.getTeacherNum())) return false;

        teacherDao.save(teacher);
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteById(int id) {
        if(!teacherDao.existsById(id)) return false;

        teacherDao.deleteById(id);
        return true;
    }

    @Override
    public List<PoTeacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public List<PoTeacher> findAllByStr(String str) {
        return teacherDao.findAllByStr("%"+str+"%");
    }
}
