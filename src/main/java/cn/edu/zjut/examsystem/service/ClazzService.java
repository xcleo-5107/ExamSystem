package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.*;
import cn.edu.zjut.examsystem.po.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClazzService implements ClazzServiceImpl{
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ClazzDao clazzDao;

    @Autowired
    private ExamSchemeDao examSchemeDao;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public List<PoClazz> findAllByClazzesStr(String targets) {
        if(targets==null) return null;

        List<PoClazz> clazzes = new ArrayList<>();
        String[] targetSplited = targets.split(",");

        for(String s:targetSplited)
        {
            //ifPresent表示一个对象是否是有值的,有则传递给后面的接口实现
            clazzDao.findById(Integer.parseInt(s)).ifPresent(clazzes::add);
        }

        if(clazzes.isEmpty()) return null;
        else return clazzes;
    }

    @Override
    public List<PoClazz> findAllByExamSchemeNum(int schemeNum) {
        PoExamScheme examScheme = examSchemeDao.findById(schemeNum).orElse(null);
        if (examScheme == null) return null;

        String clazzesStr = examScheme.getClassNum();
        return findAllByClazzesStr(clazzesStr);
    }

    @Override
    public List<PoClazz> findAll() {
        return clazzDao.findAll();
    }

    @Override
    public List<PoClazz> findAllByStudentId(int studentId) {
        return clazzDao.findAllByStudents_StudentId(studentId);
    }

    @Override
    public PoClazz findById(int id) {
        return clazzDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteStudentByStudentId(int clazzNum,int studentId) {
        PoClazz clazz = clazzDao.findById(clazzNum).orElse(null);
        if(clazz == null) return false;

        PoStudent target = studentDao.findById(studentId).orElse(null);
        if(target == null) return false;

        boolean res = clazz.getStudents().remove(target);
        clazzDao.save(clazz);
        return res;
    }

    @Override
    @Transactional
    public boolean deleteTeacherByTeacherNum(int clazzNum,int teacherNum) {
        PoClazz clazz = clazzDao.findById(clazzNum).orElse(null);
        if(clazz == null) return false;

        PoTeacher target = teacherDao.findById(teacherNum).orElse(null);
        if(target == null) return false;

        boolean res = clazz.getTeachers().remove(target);
        clazzDao.save(clazz);
        return res;
    }

    @Transactional
    @Override
    public boolean add(PoClazz clazz) {
        entityManager.persist(clazz);
        return true;
    }

    @Transactional
    @Override
    public boolean addStudent(int clazzNum, int studentId) {
        PoStudent student = studentDao.findById(studentId).orElse(null);
        if(student == null) return false;

        PoClazz clazz = clazzDao.findById(clazzNum).orElse(null);
        if(clazz == null) return false;

        if(!clazz.getStudents().contains(student)) clazz.getStudents().add(student);

        return true;
    }

    @Transactional
    @Override
    public boolean addTeacher(int clazzNum, int teacherNum) {
        PoTeacher teacher = teacherDao.findById(teacherNum).orElse(null);
        if(teacher == null) return false;

        PoClazz clazz = clazzDao.findById(clazzNum).orElse(null);
        if(clazz == null) return false;

        if(!clazz.getTeachers().contains(teacher)) clazz.getTeachers().add(teacher);

        return true;
    }
}
