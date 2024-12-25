package cn.edu.zjut.examsystem.dao;


import cn.edu.zjut.examsystem.po.PoClazz;
import cn.edu.zjut.examsystem.po.PoStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StudentDao extends JpaRepository<PoStudent, Integer> {
    List<PoStudent> findAllByStudentName(String name);

    @Query(value = "select s.student_id," +
            "s.student_name," +
            "s.student_sex," +
            "s.credit," +
            "s.semester," +
            "s.major_num " +
            "FROM student s " +
            "LEFT JOIN major AS m ON m.major_num = s.major_num " +
            "LEFT JOIN class_student AS cs ON cs.student_id = s.student_id " +
            "LEFT JOIN class AS c ON c.class_num = cs.class_num " +
            "WHERE s.student_id LIKE ?1 " +
            "OR s.student_name LIKE ?1 " +
            "OR s.student_sex LIKE ?1 " +
            "OR s.credit LIKE ?1 " +
            "OR s.semester LIKE ?1 " +
            "OR m.major_name LIKE ?1 " +
            "OR c.class_name LIKE ?1 " +
            "OR c.class_time LIKE ?1 ",nativeQuery = true)
    List<PoStudent> findAllByStr(String str);
}
