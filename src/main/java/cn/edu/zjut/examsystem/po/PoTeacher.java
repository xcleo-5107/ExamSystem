package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "teacher", schema = "exam_system")
public class PoTeacher {
    @Basic
    @Column(name = "teacher_name")
    private String teacherName;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "teacher_num")
    private int teacherNum;
    @Basic
    @Column(name = "teacher_sex")
    private String teacherSex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoTeacher poTeacher = (PoTeacher) o;
        return teacherNum == poTeacher.teacherNum && Objects.equals(teacherName, poTeacher.teacherName) && Objects.equals(teacherSex, poTeacher.teacherSex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherName, teacherNum, teacherSex);
    }
}
