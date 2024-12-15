package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.po.PoLeaderAccount;
import cn.edu.zjut.examsystem.po.PoStudentAccount;
import cn.edu.zjut.examsystem.po.PoTeacherAccount;

//用户管理业务,用于对账户操作
public interface AccountServiceImpl {
    PoStudentAccount loginAsStudent(String username,String password);
    PoTeacherAccount loginAsTeacher(String username,String password);
    PoLeaderAccount loginAsLeader(String username,String password);
}
