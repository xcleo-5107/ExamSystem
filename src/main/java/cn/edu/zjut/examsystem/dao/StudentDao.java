package cn.edu.zjut.examsystem.dao;


import cn.edu.zjut.examsystem.po.PoStudent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<PoStudent,Integer> {
}
