package cn.edu.zjut.examsystem.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "review_type")
    private PoReviewType reviewType;

    //原则上课程和学院一旦开设便不可轻易关闭,因此只做单向映射就够用了
    @ManyToMany
    @JoinTable(
            name = "course_major",
            joinColumns = @JoinColumn(name = "course_num"),
            inverseJoinColumns = @JoinColumn(name = "major_num")
    )
    private List<PoMajor> majors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoCourse poCourse = (PoCourse) o;
        return courseNum == poCourse.courseNum && Double.compare(courseCredit, poCourse.courseCredit) == 0 && Objects.equals(courseName, poCourse.courseName) && Objects.equals(coursePreiod, poCourse.coursePreiod) && Objects.equals(semester, poCourse.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseNum, courseCredit, coursePreiod, semester);
    }
}
