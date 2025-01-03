package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoExamScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ExamSchemeDao extends JpaRepository<PoExamScheme,Integer> {
    @Query(value = "select es.scheme_num, " +
            "es.scheme_begin," +
            "es.scheme_end," +
            "es.class_num," +
            "es.teacher_num," +
            "es.exam_num," +
            "es.exam_type," +
            "es.exam_review_model," +
            "es.ended," +
            "es.settlemented,"+
            "c.course_num " +
            "from exam_scheme AS es " +
            "LEFT JOIN exam_type AS et On et.type_num = es.exam_type " +
            "LEFT JOIN course AS c ON c.course_num = es.course_num " +
            "LEFT JOIN exam_review_model AS erm ON erm.model_num = es.exam_review_model "+
            "WHERE c.course_name LIKE ?1 " +
            "OR c.semester LIKE ?1 " +
            "OR et.type_name LIKE ?1 " +
            "OR erm.model_name LIKE ?1 " +
            "OR DATE_FORMAT(es.scheme_begin, '%Y-%m-%d') LIKE ?1 " +
            "OR DATE_FORMAT(es.scheme_end, '%Y-%m-%d') LIKE ?1 ",nativeQuery = true)
    List<PoExamScheme> findAllByStr(String str);

    @Query(value = "select es.scheme_num, " +
            "es.scheme_begin," +
            "es.scheme_end," +
            "es.class_num," +
            "es.teacher_num," +
            "es.exam_num," +
            "es.exam_type," +
            "es.ended," +
            "es.exam_review_model,"+
            "es.settlemented,"+
            "c.course_num " +
            "from exam_scheme AS es " +
            "JOIN exam_type AS et On et.type_num = es.exam_type " +
            "JOIN course AS c ON c.course_num = es.course_num "+
            "WHERE FIND_IN_SET(?1,es.class_num)>0 " ,nativeQuery = true)
    List<PoExamScheme> findAllByClassNum(String classNum);

    //查询索引未分配批阅任务的考试场次(时间已结束但还未标记为已结束)
    List<PoExamScheme> findAllBySchemeEndBeforeAndEndedIsFalse(Timestamp schemeEnd);

    //查询索引为期末考试但还未结算的考试场次(考试已分配任务但还未结算的)
    List<PoExamScheme> findAllByExamType_TypeNumAndSettlementedIsFalseAndEndedIsTrue(int examTypeNum);
}
