package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.ExamDao;
import cn.edu.zjut.examsystem.po.PoExam;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamService implements ExamServiceImpl{
    @Autowired
    private ExamDao examDao;

    @Autowired
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    @Override
    public PoExam findById(int id) {
        return examDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Boolean alter(PoExam exam) {
        examDao.save(exam);
        return true;
    }

    @Transactional
    public Boolean add(PoExam exam)
    {
        entityManager.persist(exam);
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteById(int id) {
        if(examDao.existsById(id))
        {
            examDao.deleteById(id);
            return true;
        }
        else return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PoExam> findAllByStr(String str) {
        return examDao.findAllByStr("%"+str+"%");
    }

    @Transactional(readOnly = true)
    public List<PoExam> findAll()
    {
        return examDao.findAll();
    }


}
