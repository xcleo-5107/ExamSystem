package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "exam_review_model",schema = "exam_system")
public class PoExamReviewModel {
    @Id
    @Column(name = "model_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int modelNum;

    @Basic
    @Column(name = "model_name")
    private String modelName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoExamReviewModel that = (PoExamReviewModel) o;
        return modelNum == that.modelNum && Objects.equals(modelName, that.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelNum,modelName);
    }
}
