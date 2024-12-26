package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "task_list_teacher_exercise_type_model", schema = "exam_system")
public class PoTaskListTeacherExerciseTypeModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "teacher_num")
    private Integer teacherNum;
    @Basic
    @Column(name = "exercise_type")
    private Integer exerciseType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoTaskListTeacherExerciseTypeModel that = (PoTaskListTeacherExerciseTypeModel) o;
        return id == that.id && Objects.equals(teacherNum, that.teacherNum) && Objects.equals(exerciseType, that.exerciseType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherNum, exerciseType);
    }
}
