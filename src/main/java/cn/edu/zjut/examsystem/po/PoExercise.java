package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "exercise", schema = "exam_system")
public class PoExercise {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exercise_num")
    private int exerciseNum;
    @Basic
    @Column(name = "type_num")
    private Integer typeNum;
    @Basic
    @Column(name = "course_num")
    private Integer courseNum;
    @Basic
    @Column(name = "exercise_info")
    private String exerciseInfo;
    @Basic
    @Column(name = "exercise_problem")
    private String exerciseProblem;
    @Basic
    @Column(name = "exercise_answer")
    private String exerciseAnswer;
    @Basic
    @Column(name = "exercise_other")
    private byte[] exerciseOther;
    @Basic
    @Column(name = "exercise_difficulty")
    private Integer exerciseDifficulty;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoExercise that = (PoExercise) o;
        return exerciseNum == that.exerciseNum && Objects.equals(typeNum, that.typeNum) && Objects.equals(courseNum, that.courseNum) && Objects.equals(exerciseInfo, that.exerciseInfo) && Objects.equals(exerciseProblem, that.exerciseProblem) && Objects.equals(exerciseAnswer, that.exerciseAnswer) && Arrays.equals(exerciseOther, that.exerciseOther) && Objects.equals(exerciseDifficulty, that.exerciseDifficulty);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(exerciseNum, typeNum, courseNum, exerciseInfo, exerciseProblem, exerciseAnswer, exerciseDifficulty);
        result = 31 * result + Arrays.hashCode(exerciseOther);
        return result;
    }
}
