package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "exam", schema = "exam_system")
public class PoExam {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exam_num")
    private int examNum;
    @Basic
    @Column(name = "course_num")
    private Integer courseNum;
    @Basic
    @Column(name = "teacher_num")
    private Integer teacherNum;
    @Basic
    @Column(name = "is_released")
    private Byte isReleased;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoExam poExam = (PoExam) o;
        return examNum == poExam.examNum && Objects.equals(courseNum, poExam.courseNum) && Objects.equals(teacherNum, poExam.teacherNum) && Objects.equals(isReleased, poExam.isReleased);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examNum, courseNum, teacherNum, isReleased);
    }
}
