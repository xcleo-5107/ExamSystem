package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoLeaderAccount;
import org.springframework.data.repository.CrudRepository;

public interface LeaderAccountDao extends CrudRepository<PoLeaderAccount,Integer> {
    PoLeaderAccount findByUsernameAndPassword(String username,String password);
}
