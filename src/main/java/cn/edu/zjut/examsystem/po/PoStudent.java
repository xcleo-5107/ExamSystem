package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "student", schema = "exam_system")
public class PoStudent {
    @Basic
    @Column(name = "student_name")
    private String studentName;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "student_id")
    private int studentId;
    @Basic
    @Column(name = "student_sex")
    private String studentSex;
    @Basic
    @Column(name = "major_num")
    private Integer majorNum;
    @Basic
    @Column(name = "credit")
    private Double credit;
    @Basic
    @Column(name = "semester")
    private String semester;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoStudent poStudent = (PoStudent) o;
        return studentId == poStudent.studentId && Objects.equals(studentName, poStudent.studentName) && Objects.equals(studentSex, poStudent.studentSex) && Objects.equals(majorNum, poStudent.majorNum) && Objects.equals(credit, poStudent.credit) && Objects.equals(semester, poStudent.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, studentId, studentSex, majorNum, credit, semester);
    }
}
