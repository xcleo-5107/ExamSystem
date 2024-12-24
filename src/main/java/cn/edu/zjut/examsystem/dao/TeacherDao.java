package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao extends JpaRepository<PoTeacher,Integer> {
    @Query(value = "select t.teacher_num," +
            "t.teacher_name," +
            "t.teacher_sex," +
            "cl.class_num," +
            "co.course_num " +
            "from teacher t " +
            "join class_teacher as clt on clt.teacher_num = t.teacher_num " +
            "join class as cl on clt.class_num = cl.class_num " +
            "join course_teacher as cot on cot.teacher_num = t.teacher_num " +
            "join course as co on co.course_num = cot.course_num " +
            "where t.teacher_name LIKE ?1 " +
            "or t.teacher_sex LIKE ?1 " +
            "or cl.class_name LIKE ?1 " +
            "or cl.class_time LIKE ?1 " +
            "or co.course_name LIKE ?1 ",nativeQuery = true)
    List<PoTeacher> findAllByStr(String str);
}
