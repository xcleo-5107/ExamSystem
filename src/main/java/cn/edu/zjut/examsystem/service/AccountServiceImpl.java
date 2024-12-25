package cn.edu.zjut.examsystem.service;

import cn.edu.zjut.examsystem.Enum.LoginStatus;
import cn.edu.zjut.examsystem.dto.AccountDto;
import cn.edu.zjut.examsystem.po.PoLeaderAccount;
import cn.edu.zjut.examsystem.po.PoStudentAccount;
import cn.edu.zjut.examsystem.po.PoTeacherAccount;

//用户管理业务,用于对账户操作
public interface AccountServiceImpl {
    int loginAsStudent(AccountDto accountDto);
    int loginAsTeacher(AccountDto accountDto);
    int loginAsLeader(AccountDto accountDto);
}
