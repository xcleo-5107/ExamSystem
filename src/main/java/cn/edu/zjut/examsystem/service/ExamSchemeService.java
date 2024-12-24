package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.ExamSchemeDao;
import cn.edu.zjut.examsystem.dao.StudentDao;
import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.po.PoExamScheme;
import cn.edu.zjut.examsystem.po.PoStudent;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamSchemeService implements ExamSchemeServiceImpl{
    @Autowired
    private ExamSchemeDao examSchemeDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<PoExamScheme> findAllByStr(String str) {

        return examSchemeDao.findAllByStr("%"+str+"%");
    }

    @Override
    public List<PoExamScheme> findAll() {
        return examSchemeDao.findAll();
    }

    @Override
    public List<PoExamScheme> findAllByStudentId(int studentId) {
        PoStudent student = studentDao.findById(studentId).orElse(null);
        if(student == null) return null;

        List<PoClazz> clazzes = student.getClazzes();
        List<PoExamScheme> examSchemes = new ArrayList<>();

        for(PoClazz clazz:student.getClazzes())
        {
            String clazzNum = Integer.toString(clazz.getClassNum());

            List<PoExamScheme> examSchemesTemp = examSchemeDao.findAllByClassNum(clazzNum);

            for(PoExamScheme examScheme:examSchemesTemp)
            {
                if(!examSchemes.contains(examScheme)) examSchemes.add(examScheme);
            }
        }

        return examSchemes;
    }

    @Transactional
    @Override
    public Boolean alter(PoExamScheme examScheme) {
        examSchemeDao.save(examScheme);
        return true;
    }

    @Transactional
    @Override
    public Boolean add(PoExamScheme examScheme) {
        entityManager.persist(examScheme);
        return true;
    }
}
