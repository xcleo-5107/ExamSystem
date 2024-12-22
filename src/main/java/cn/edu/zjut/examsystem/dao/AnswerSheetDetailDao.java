package cn.edu.zjut.examsystem.dao;

import cn.edu.zjut.examsystem.po.PoAnswerSheetDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerSheetDetailDao extends JpaRepository<PoAnswerSheetDetail,Integer> {
    @Query("update PoAnswerSheetDetail a " +
            "set a.score = ?2 " +
            "where a.detailId = ?1")
    @Modifying
    int saveScore(int detail_id,int score);
}
