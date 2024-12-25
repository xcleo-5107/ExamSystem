package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoExamScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamSchemeDao extends JpaRepository<PoExamScheme,Integer> {
    @Query(value = "select es.scheme_num, " +
            "es.scheme_begin," +
            "es.scheme_end," +
            "es.class_num," +
            "es.teacher_num," +
            "es.exam_num," +
            "es.exam_type,"+
            "c.course_num " +
            "from exam_scheme AS es " +
            "LEFT JOIN exam_type AS et On et.type_num = es.exam_type " +
            "LEFT JOIN course AS c ON c.course_num = es.course_num "+
            "WHERE c.course_name LIKE ?1 " +
            "OR c.semester LIKE ?1 " +
            "OR et.type_name LIKE ?1 " +
            "OR DATE_FORMAT(es.scheme_begin, '%Y-%m-%d') LIKE ?1 " +
            "OR DATE_FORMAT(es.scheme_end, '%Y-%m-%d') LIKE ?1 ",nativeQuery = true)
    List<PoExamScheme> findAllByStr(String str);

    @Query(value = "select es.scheme_num, " +
            "es.scheme_begin," +
            "es.scheme_end," +
            "es.class_num," +
            "es.teacher_num," +
            "es.exam_num," +
            "es.exam_type,"+
            "c.course_num " +
            "from exam_scheme AS es " +
            "JOIN exam_type AS et On et.type_num = es.exam_type " +
            "JOIN course AS c ON c.course_num = es.course_num "+
            "WHERE FIND_IN_SET(?1,es.class_num)>0 " ,nativeQuery = true)
    List<PoExamScheme> findAllByClassNum(String classNum);
}
