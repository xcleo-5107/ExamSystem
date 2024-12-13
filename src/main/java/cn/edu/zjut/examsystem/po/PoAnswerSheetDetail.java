package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "answer_sheet_detail", schema = "exam_system")
public class PoAnswerSheetDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "detail_id")
    private int detailId;
    @Basic
    @Column(name = "sheet_num")
    private Integer sheetNum;
    @Basic
    @Column(name = "module_num")
    private Integer moduleNum;
    @Basic
    @Column(name = "exercise_in_module_num")
    private Integer exerciseInModuleNum;
    @Basic
    @Column(name = "answer")
    private String answer;
    @Basic
    @Column(name = "score")
    private Integer score;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoAnswerSheetDetail that = (PoAnswerSheetDetail) o;
        return detailId == that.detailId && Objects.equals(sheetNum, that.sheetNum) && Objects.equals(moduleNum, that.moduleNum) && Objects.equals(exerciseInModuleNum, that.exerciseInModuleNum) && Objects.equals(answer, that.answer) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, sheetNum, moduleNum, exerciseInModuleNum, answer, score);
    }
}
