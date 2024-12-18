package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.dao.LeaderAccountDao;
import cn.edu.zjut.examsystem.dao.StudentAccountDao;
import cn.edu.zjut.examsystem.dao.TeacherAccountDao;
import cn.edu.zjut.examsystem.po.PoLeaderAccount;
import cn.edu.zjut.examsystem.po.PoStudentAccount;
import cn.edu.zjut.examsystem.po.PoTeacherAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements AccountServiceImpl{
    @Autowired
    private StudentAccountDao studentAccountDao;

    @Autowired
    private TeacherAccountDao teacherAccountDao;

    @Autowired
    private LeaderAccountDao leaderAccountDao;

    @Transactional(readOnly = true)
    @Override
    public PoStudentAccount loginAsStudent(String username, String password) {
        return studentAccountDao.findByUsernameAndPassword(username, password);
    }

    @Transactional(readOnly = true)
    @Override
    public PoTeacherAccount loginAsTeacher(String username, String password) {
        return teacherAccountDao.findByUsernameAndPassword(username, password);
    }

    @Transactional(readOnly = true)
    @Override
    public PoLeaderAccount loginAsLeader(String username, String password) {
        return leaderAccountDao.findByUsernameAndPassword(username, password);
    }

}
