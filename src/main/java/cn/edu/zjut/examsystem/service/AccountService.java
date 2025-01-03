package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.Enum.LoginStatus;
import cn.edu.zjut.examsystem.dao.*;
import cn.edu.zjut.examsystem.dto.AccountDto;
import cn.edu.zjut.examsystem.po.PoLeaderAccount;
import cn.edu.zjut.examsystem.po.PoStudent;
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
    private StudentDao studentDao;

    @Autowired
    private TeacherAccountDao teacherAccountDao;
    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private LeaderAccountDao leaderAccountDao;

    @Transactional(readOnly = true)
    @Override
    public int loginAsStudent(AccountDto accountDto) {
        PoStudentAccount studentAccount = studentAccountDao.findByUsernameAndPassword(accountDto.getUsername(), accountDto.getPassword());
        if(studentAccount == null) return LoginStatus.UserNameOrPasswordFail.getValue();

        if(studentAccount.getStudentId() == null) return LoginStatus.AccountHaveNoEntity.getValue();

        else
        {
            if(studentDao.existsById(studentAccount.getStudentId()))
            {
                return studentAccount.getStudentId();
            }
            else return LoginStatus.EntityNotFound.getValue();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public int loginAsTeacher(AccountDto accountDto) {
        PoTeacherAccount teacherAccount = teacherAccountDao.findByUsernameAndPassword(accountDto.getUsername(), accountDto.getPassword());
        if(teacherAccount == null) return LoginStatus.UserNameOrPasswordFail.getValue();

        if(teacherAccount.getTeacherNum() == null) return LoginStatus.AccountHaveNoEntity.getValue();

        else
        {
            if(teacherDao.existsById(teacherAccount.getTeacherNum()))
            {
                return teacherAccount.getTeacherNum();
            }
            else return LoginStatus.EntityNotFound.getValue();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public int loginAsLeader(AccountDto accountDto) {
        PoLeaderAccount leaderAccount = leaderAccountDao.findByUsernameAndPassword(accountDto.getUsername(), accountDto.getPassword());
        if(leaderAccount == null) return LoginStatus.UserNameOrPasswordFail.getValue();
        else return 0;
    }

}
