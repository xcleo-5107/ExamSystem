package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoStudentAccount;
import org.springframework.data.repository.CrudRepository;

public interface StudentAccountDao extends CrudRepository<PoStudentAccount,Integer> {
    PoStudentAccount findByUsernameAndPassword(String username,String password);
}
