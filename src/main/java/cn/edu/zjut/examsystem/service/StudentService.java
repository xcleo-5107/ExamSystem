package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.StudentDao;
import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.po.PoStudent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentServiceImpl{
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

    public PoStudent addStudent(PoStudent student)
    {
        return studentDao.save(student);
    }

}
