package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "exam_scheme", schema = "exam_system")
public class PoExamScheme {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "scheme_num")
    private int schemeNum;
    @Basic
    @Column(name = "scheme_begin")
    private Timestamp schemeBegin;
    @Basic
    @Column(name = "scheme_end")
    private Timestamp schemeEnd;

    @Basic
    @Column(name = "class_num")
    private String classNum;

    @Basic
    @Column(name = "teacher_num")
    private String teacherNum;

    //为了不暴露试卷数据,不直接引用试卷
    @Basic
    @Column(name = "exam_num")
    private Integer examNum;

    @Column(name = "ended")
    private Boolean ended;

    @ManyToOne
    @JoinColumn(name = "exam_type")
    private PoExamType examType;

    @ManyToOne
    @JoinColumn(name = "course_num")
    private PoCourse course;

    @ManyToOne
    @JoinColumn(name = "exam_review_model")
    private PoExamReviewModel examReviewModel;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoExamScheme that = (PoExamScheme) o;
        return schemeNum == that.schemeNum && ended == that.ended && Objects.equals(schemeBegin, that.schemeBegin) && Objects.equals(examNum, that.examNum) && Objects.equals(schemeEnd, that.schemeEnd) && Objects.equals(examType, that.examType) && Objects.equals(classNum, that.classNum) && Objects.equals(teacherNum, that.teacherNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeNum,examNum, schemeBegin, schemeEnd, examType, classNum, teacherNum,ended);
    }
}
