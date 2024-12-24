package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.CourseDao;
import cn.edu.zjut.examsystem.po.PoCourse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService implements CourseServiceImpl{
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<PoCourse> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<PoCourse> findAllByStr(String str) {
        return courseDao.findAllByStr("%"+str+"%");
    }

    @Transactional
    @Override
    public Boolean add(PoCourse course) {
        entityManager.persist(course);

        return true;
    }

    @Transactional
    @Override
    public Boolean alter(PoCourse course) {
        courseDao.save(course);
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteById(int id) {
        if(!courseDao.existsById(id)) return false;

        courseDao.deleteById(id);
        return true;
    }


}
