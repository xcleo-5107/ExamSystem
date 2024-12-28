package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "exam", schema = "exam_system")
public class PoExam {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exam_num")
    private int examNum;

    @Basic
    @Column(name = "is_released")
    private Byte isReleased;

    @Basic
    @NotNull
    @Column(name = "total_score")
    private int totalScore;

    @ManyToOne
    @JoinColumn(name = "teacher_num")
    private PoTeacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_num")
    private PoCourse course;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_num")
    @OrderBy("moduleInExamNum asc")
    private List<PoExamModule> modules;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoExam poExam = (PoExam) o;
        return examNum == poExam.examNum && Objects.equals(isReleased, poExam.isReleased);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examNum,isReleased);
    }
}
