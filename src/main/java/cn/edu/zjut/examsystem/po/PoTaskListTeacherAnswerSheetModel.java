package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "task_list_teacher_answer_sheet_model", schema = "exam_system")
public class PoTaskListTeacherAnswerSheetModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "teacher_num")
    private Integer teacherNum;
    @Basic
    @Column(name = "answer_sheet_num")
    private Integer answerSheetNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoTaskListTeacherAnswerSheetModel that = (PoTaskListTeacherAnswerSheetModel) o;
        return id == that.id && Objects.equals(teacherNum, that.teacherNum) && Objects.equals(answerSheetNum, that.answerSheetNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherNum, answerSheetNum);
    }
}
