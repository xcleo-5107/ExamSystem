package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.ExamSchemeDao;
import cn.edu.zjut.examsystem.po.PoExamScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSchemeService implements ExamSchemeServiceImpl{
    @Autowired
    private ExamSchemeDao examSchemeDao;

    @Override
    public List<PoExamScheme> findAllByStr(String str) {

        return examSchemeDao.findAllByStr("%"+str+"%");
    }

    @Override
    public PoExamScheme alter(PoExamScheme examScheme) {
        return examSchemeDao.save(examScheme);
    }
}
