package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.CourseDao;
import cn.edu.zjut.examsystem.dao.StudentDao;
import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.po.PoCourse;
import cn.edu.zjut.examsystem.po.PoStudent;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements CourseServiceImpl{
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<PoCourse> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<PoCourse> findAllByStr(String str) {
        return courseDao.findAllByStr("%"+str+"%");
    }

    @Override
    public List<PoCourse> findByStudentId(int studentId) {
        PoStudent student = studentDao.findById(studentId).orElse(null);
        if(student == null) return null;

        List<PoClazz> clazzes = student.getClazzes();
        if(clazzes == null || clazzes.isEmpty()) return null;

        List<PoCourse> courses = new ArrayList<>();
        for(PoClazz clazz:clazzes)
        {
            PoCourse course = courseDao.findById(clazz.getCourseNum()).orElse(null);
            if(course == null) continue;

            if(!courses.contains(course)) courses.add(course);
        }

        return courses;
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
