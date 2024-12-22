package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "answer_sheet", schema = "exam_system")
public class PoAnswerSheet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sheet_num")
    private int sheetNum;
    @Basic
    @Column(name = "scheme_num")
    private Integer schemeNum;
    @Basic
    @Column(name = "student_id")
    private Integer studentId;
    @Basic
    @Column(name = "total_score")
    private Integer totalScore;

    @OneToMany
    @JoinColumn(name = "sheet_num")
    private List<PoAnswerSheetDetail> answerSheetDetails;

    @ManyToOne
    @JoinColumn(name = "sheet_status")
    private PoAnswerSheetStatusType statusType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoAnswerSheet that = (PoAnswerSheet) o;
        return sheetNum == that.sheetNum && Objects.equals(schemeNum, that.schemeNum) && Objects.equals(studentId, that.studentId) && Objects.equals(totalScore, that.totalScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sheetNum, schemeNum, studentId, totalScore);
    }
}
