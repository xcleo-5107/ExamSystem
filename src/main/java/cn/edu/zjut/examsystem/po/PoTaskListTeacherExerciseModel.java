package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "task_list_teacher_exercise_model", schema = "exam_system")
public class PoTaskListTeacherExerciseModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "teacher_num")
    private Integer teacherNum;
    @Basic
    @Column(name = "model_exercise_id")
    private Integer modelExerciseId;

    @Basic
    @Column(name = "exam_scheme_num")
    private Integer examSchemeNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoTaskListTeacherExerciseModel that = (PoTaskListTeacherExerciseModel) o;
        return id == that.id && Objects.equals(examSchemeNum, that.examSchemeNum) && Objects.equals(teacherNum, that.teacherNum) && Objects.equals(modelExerciseId, that.modelExerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherNum,examSchemeNum, modelExerciseId);
    }
}
