package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.AnswerSheetDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerSheetDetailService implements AnswerSheetDetailServiceImpl{
    @Autowired
    private AnswerSheetDetailDao answerSheetDetailDao;

    @Override
    @Transactional
    public int saveScore(int detailId, int score) {
        return answerSheetDetailDao.saveScore(detailId,score);
    }
}
