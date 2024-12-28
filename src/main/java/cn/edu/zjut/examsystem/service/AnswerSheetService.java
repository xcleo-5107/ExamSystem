package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.AnswerSheetDao;
import cn.edu.zjut.examsystem.po.PoAnswerSheet;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerSheetService implements AnswerSheetServiceImpl{
    @Autowired
    private AnswerSheetDao answerSheetDao;
    @Autowired
    private EntityManager entityManager;


    @Override
    public List<PoAnswerSheet> findAllByExamSchemeNum(int schemeNum) {
        return answerSheetDao.findAllBySchemeNum(schemeNum);
    }

    @Override
    public PoAnswerSheet findBySheetNum(int num) {
        return answerSheetDao.findById(num);
    }

    @Override
    public PoAnswerSheet findByStudentIdAndSchemeNum(int studentId, int schemeNum) {
        return answerSheetDao.findByStudentIdAndSchemeNum(studentId,schemeNum);
    }

    @Override
    @Transactional
    public boolean add(PoAnswerSheet answerSheet) {
        entityManager.persist(answerSheet);
        return true;
    }
}
