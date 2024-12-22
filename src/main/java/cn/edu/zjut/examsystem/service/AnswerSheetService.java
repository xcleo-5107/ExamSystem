package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.AnswerSheetDao;
import cn.edu.zjut.examsystem.po.PoAnswerSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerSheetService implements AnswerSheetServiceImpl{
    @Autowired
    private AnswerSheetDao answerSheetDao;


    @Override
    public List<PoAnswerSheet> findAllByExamSchemeNum(int schemeNum) {
        return answerSheetDao.findAllBySchemeNum(schemeNum);
    }

    @Override
    public PoAnswerSheet findBySheetNum(int num) {
        return answerSheetDao.findById(num);
    }
}
