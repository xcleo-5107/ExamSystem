package cn.edu.zjut.examsystem.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course", schema = "exam_system")
public class PoCourse {
    @Basic
    @Column(name = "course_name")
    private String courseName;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "course_num")
    private int courseNum;
    @Basic
    @Column(name = "course_credit")
    private double courseCredit;
    @Basic
    @Column(name = "course_preiod")
    private Integer coursePreiod;
    @Basic
    @Column(name = "review_type")
    private Integer reviewType;
    @Basic
    @Column(name = "major_num")
    private Integer majorNum;
    @Basic
    @Column(name = "semester")
    private String semester;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_num"),
            inverseJoinColumns = @JoinColumn(name = "teacher_num")
    )
    @JsonIgnore
    private Set<PoTeacher> teachers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoCourse poCourse = (PoCourse) o;
        return courseNum == poCourse.courseNum && Double.compare(courseCredit, poCourse.courseCredit) == 0 && Objects.equals(courseName, poCourse.courseName) && Objects.equals(coursePreiod, poCourse.coursePreiod) && Objects.equals(reviewType, poCourse.reviewType) && Objects.equals(majorNum, poCourse.majorNum) && Objects.equals(semester, poCourse.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseNum, courseCredit, coursePreiod, reviewType, majorNum, semester);
    }
}
