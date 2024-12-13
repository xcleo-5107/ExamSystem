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
    @Column(name = "exam_num")
    private Integer examNum;
    @Basic
    @Column(name = "scheme_begin")
    private Timestamp schemeBegin;
    @Basic
    @Column(name = "scheme_end")
    private Timestamp schemeEnd;
    @Basic
    @Column(name = "course_num")
    private Integer courseNum;
    @Basic
    @Column(name = "exam_type")
    private Integer examType;
    @Basic
    @Column(name = "class_num")
    private String classNum;
    @Basic
    @Column(name = "teacher_nums")
    private String teacherNums;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoExamScheme that = (PoExamScheme) o;
        return schemeNum == that.schemeNum && Objects.equals(examNum, that.examNum) && Objects.equals(schemeBegin, that.schemeBegin) && Objects.equals(schemeEnd, that.schemeEnd) && Objects.equals(courseNum, that.courseNum) && Objects.equals(examType, that.examType) && Objects.equals(classNum, that.classNum) && Objects.equals(teacherNums, that.teacherNums);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeNum, examNum, schemeBegin, schemeEnd, courseNum, examType, classNum, teacherNums);
    }
}
