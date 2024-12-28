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

    //这种需要在添加答题表时一并添加的需要cascade = CascadeType.ALL开启级联更新
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sheet_num")
    private List<PoAnswerSheetDetail> answerSheetDetails;

    //引用的数据不需要级联,以免引用一个不存在的数据时也去新建一个
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
