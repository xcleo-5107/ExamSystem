package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends JpaRepository<PoCourse,Integer> {
    @Query(value = "select c.course_name," +
            "c.course_num," +
            "c.course_credit," +
            "c.course_preiod," +
            "c.review_type," +
            "c.major_num," +
            "c.semester " +
            "From course c " +
            "LEFT JOIN review_type AS rt ON rt.type_num = c.review_type " +
            "LEFT JOIN major AS m ON m.major_num = c.major_num " +
            "WHERE c.course_name LIKE ?1 " +
            "OR c.course_credit LIKE ?1 " +
            "OR c.course_preiod LIKE ?1 " +
            "OR rt.type_name LIKE ?1 " +
            "OR m.major_name LIKE ?1 " +
            "OR c.semester LIKE ?1 ",nativeQuery = true)
    List<PoCourse> findAllByStr(String str);
}
