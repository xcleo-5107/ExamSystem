package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.ClazzDao;
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
    @Autowired
    private ClazzDao clazzDao;

    @Override
    public PoStudent findByStudentId(int id) {

        return studentDao.findById(id).orElse(null);
    }

    @Override
    public List<PoStudent> findAllByStudentName(String name) {
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

    @Transactional
    @Override
    public Boolean alter(PoStudent student) {
        studentDao.save(student);
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteById(int id) {

        if(!studentDao.existsById(id)) return false;
        else
        {
            PoStudent student = studentDao.findById(id).orElse(null);

            List<PoClazz> clazzes = clazzDao.findAllByStudents_StudentId(id);
            if(clazzes!=null && !clazzes.isEmpty())
            {
                for(PoClazz clazz:clazzes)
                {
                    clazz.getStudents().remove(student);
                }
            }

            studentDao.deleteById(id);
        }

        return true;
    }

}
