package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoTeacherAccount;
import org.springframework.data.repository.CrudRepository;

public interface TeacherAccountDao extends CrudRepository<PoTeacherAccount,Integer> {
    PoTeacherAccount findByUsernameAndPassword(String username,String password);
}
