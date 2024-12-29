package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoAnswerSheetDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerSheetDetailDao extends JpaRepository<PoAnswerSheetDetail,Integer> {
    @Query("update PoAnswerSheetDetail a " +
            "set a.score = ?2 " +
            "where a.detailId = ?1")
    @Modifying
    int saveScore(int detail_id,int score);

    //获取所有答题表的某一题型的部分(已弃用)
//    @Query(value = "select asd.detail_id," +
//            "asd.sheet_num," +
//            "asd.answer," +
//            "asd.score," +
//            "asd.module_exercise_id " +
//            "from answer_sheet_detail as asd " +
//            "join answer_sheet as _as on _as.sheet_num = asd.sheet_num " +
//            "join module_exercise as me on me.module_exercise_id = asd.module_exercise_id " +
//            "join exam_module as em on em.module_num = me.module_num " +
//            "where _as.scheme_num = ?1 " +
//            "and em.exercise_type = ?2 ",nativeQuery = true)
//    List<PoAnswerSheetDetail> findAllBySchemeNumAndExerciseType(int schemeNum,int exerciseTypeNum);

    @Query(value = "select score from answer_sheet_detail where detail_id = ?1",nativeQuery = true)
    Object findScoreByid(int id);

    //根据试卷小题获取所有的答题表的该部分
    @Query(value = "select asd.detail_id," +
            "asd.sheet_num," +
            "asd.answer," +
            "asd.score," +
            "asd.module_exercise_id " +
            "from answer_sheet_detail as asd " +
            "join answer_sheet as _as on _as.sheet_num = asd.sheet_num " +
            "join module_exercise as me on me.module_exercise_id = asd.module_exercise_id " +
            "where _as.scheme_num = ?2  " +
            "AND asd.module_exercise_id = ?1 ",nativeQuery = true)
    List<PoAnswerSheetDetail> findAllByModuleExerciseIdAndExamSchemeNum(int moduleExerciseId ,int examSchemeNum);
}
