package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "major", schema = "exam_system")
public class PoMajor {
    @Basic
    @Column(name = "major_name")
    private String majorName;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "major_num")
    private int majorNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoMajor poMajor = (PoMajor) o;
        return majorNum == poMajor.majorNum && Objects.equals(majorName, poMajor.majorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(majorName, majorNum);
    }
}
