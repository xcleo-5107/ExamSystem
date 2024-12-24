package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.StudentDao;
import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.po.PoStudent;
import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    public List<PoStudent> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<PoStudent> findAllByStr(String str) {
        return studentDao.findAllByStr("%"+str+"%");
    }

    @Transactional
    @Override
    public Boolean add(PoStudent student)
    {
        entityManager.persist(student);
        return true;
    }

    @Override
    public Boolean alter(PoStudent student) {
        studentDao.save(student);
        return true;
    }

    @Override
    public Boolean deleteById(int id) {
        if(!studentDao.existsById(id)) return false;
        else studentDao.deleteById(id);

        return true;
    }

}
