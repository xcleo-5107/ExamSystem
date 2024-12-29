package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.AnswerSheetDao;
import cn.edu.zjut.examsystem.dao.AnswerSheetDetailDao;
import cn.edu.zjut.examsystem.po.PoAnswerSheet;
import cn.edu.zjut.examsystem.po.PoAnswerSheetDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerSheetDetailService implements AnswerSheetDetailServiceImpl{
    @Autowired
    private AnswerSheetDetailDao answerSheetDetailDao;

    @Autowired
    private AnswerSheetDao answerSheetDao;

    @Override
    @Transactional
    public int saveScore(int detailId, int score) {
        PoAnswerSheetDetail answerSheetDetail = answerSheetDetailDao.findById(detailId).orElse(null);
        if (answerSheetDetail == null) return 0;

        int old = 0;
        if(answerSheetDetail.getScore() != null)  old = answerSheetDetail.getScore();

        int add = score - old;
        PoAnswerSheet answerSheet = answerSheetDao.findById(answerSheetDetail.getSheetNum()).orElse(null);

        if(answerSheet == null) return 0;

        int oldTotalScore = 0;
        if(answerSheet.getTotalScore()!=null) oldTotalScore = answerSheet.getTotalScore();
        answerSheet.setTotalScore(oldTotalScore+add);

        return answerSheetDetailDao.saveScore(detailId,score);
    }

    @Override
    public List<PoAnswerSheetDetail> findAllByModuleExerciseIdAndExamSchemeNum(int moduleExerciseId,int examSchemeNum) {
        return answerSheetDetailDao.findAllByModuleExerciseIdAndExamSchemeNum(moduleExerciseId,examSchemeNum);
    }
}
