package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.ExamDao;
import cn.edu.zjut.examsystem.dao.ExamModuleDao;
import cn.edu.zjut.examsystem.po.PoExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService implements ExamServiceImpl{
    @Autowired
    private ExamDao examDao;


    @Override
    public PoExam findById(int id) {
        return examDao.findById(id).orElse(null);
    }
}
