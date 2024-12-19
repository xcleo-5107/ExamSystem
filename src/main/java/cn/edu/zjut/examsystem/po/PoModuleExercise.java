package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "module_exercise", schema = "exam_system")
public class PoModuleExercise {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "exercise_in_module_num")
    private Integer exerciseInModuleNum;

    @ManyToOne
    @JoinColumn(name = "exercise_num")
    private PoExercise exercise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoModuleExercise that = (PoModuleExercise) o;
        return id == that.id && Objects.equals(exerciseInModuleNum, that.exerciseInModuleNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exerciseInModuleNum);
    }
}
