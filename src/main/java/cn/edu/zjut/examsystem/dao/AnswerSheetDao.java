package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoAnswerSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerSheetDao extends JpaRepository<PoAnswerSheet,Integer> {
    //JPQL语言,在引用不超过一层数据的情况下可以使用
    @Query("SELECT a " +
            "FROM PoAnswerSheet a " +
            "JOIN FETCH a.answerSheetDetails d " +
            "JOIN FETCH d.moduleExercise m " +
            "WHERE a.schemeNum = ?1 " +
            "ORDER BY m.moduleNum ASC, m.exerciseInModuleNum ASC")
    List<PoAnswerSheet> findAllBySchemeNum(int schemeNum);

    @Query("SELECT a " +
            "FROM PoAnswerSheet a " +
            "JOIN FETCH a.answerSheetDetails d " +
            "JOIN FETCH d.moduleExercise m " +
            "WHERE a.sheetNum = ?1 " +
            "ORDER BY m.moduleNum ASC, m.exerciseInModuleNum ASC")
    PoAnswerSheet findById(int id);

    PoAnswerSheet findByStudentIdAndSchemeNum(int studentId,int schemeNum);
}
