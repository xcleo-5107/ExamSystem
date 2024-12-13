package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "review_type", schema = "exam_system")
public class PoReviewType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "type_num")
    private int typeNum;
    @Basic
    @Column(name = "type_name")
    private String typeName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoReviewType that = (PoReviewType) o;
        return typeNum == that.typeNum && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeNum, typeName);
    }
}
