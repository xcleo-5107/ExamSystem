package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "answer_sheet_status_type", schema = "exam_system")
public class PoAnswerSheetStatusType {
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
        PoAnswerSheetStatusType that = (PoAnswerSheetStatusType) o;
        return typeNum == that.typeNum && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeNum, typeName);
    }
}
