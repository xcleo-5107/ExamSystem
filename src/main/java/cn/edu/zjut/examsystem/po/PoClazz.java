package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class", schema = "exam_system")
public class PoClazz {
    @Basic
    @Column(name = "class_name")
    private String className;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "class_num")
    private int classNum;
    @Basic
    @Column(name = "course_num")
    private Integer courseNum;
    @Basic
    @Column(name = "class_time")
    private String classTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="class_student",
            joinColumns = @JoinColumn(name="class_num"),
            inverseJoinColumns = @JoinColumn(name="student_id")
    )
    private Set<PoStudent> students;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "class_teacher",
            joinColumns = @JoinColumn(name = "class_num"),
            inverseJoinColumns = @JoinColumn(name = "teacher_num")
    )
    private Set<PoTeacher> teachers;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoClazz poClazz = (PoClazz) o;
        return classNum == poClazz.classNum && Objects.equals(className, poClazz.className) && Objects.equals(courseNum, poClazz.courseNum) && Objects.equals(classTime, poClazz.classTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, classNum, courseNum, classTime);
    }
}
