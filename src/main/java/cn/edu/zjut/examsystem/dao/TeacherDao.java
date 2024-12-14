package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoTeacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao extends CrudRepository<PoTeacher,Integer> {
}
