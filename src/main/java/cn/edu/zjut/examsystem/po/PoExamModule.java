package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "exam_module", schema = "exam_system")
public class PoExamModule {
    @Basic
    @Column(name = "exam_num")
    private Integer examNum;
    @Basic
    @Column(name = "exercise_type")
    private Integer exerciseType;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "module_num")
    private int moduleNum;
    @Basic
    @Column(name = "module_in_exam_num")
    private Integer moduleInExamNum;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoExamModule that = (PoExamModule) o;
        return moduleNum == that.moduleNum && Objects.equals(examNum, that.examNum) && Objects.equals(exerciseType, that.exerciseType) && Objects.equals(moduleInExamNum, that.moduleInExamNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examNum, exerciseType, moduleNum, moduleInExamNum);
    }
}
