package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.StudentDao;
import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.po.PoStudent;
import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentServiceImpl{
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private StudentDao studentDao;

    @Override
    public PoStudent findByStudentId(int id) {

        return studentDao.findById(id).orElse(null);
    }

    @Override
    public Set<PoStudent> findAllByStudentName(String name) {
        return studentDao.findAllByStudentName(name);
    }

    @Transactional
    public Boolean addStudent(PoStudent student)
    {
        studentDao.save(student);
        return true;
    }

}
