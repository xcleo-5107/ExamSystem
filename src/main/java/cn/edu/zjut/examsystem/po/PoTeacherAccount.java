package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "teacher_account", schema = "exam_system")
public class PoTeacherAccount {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_num")
    private int accountNum;

    @Basic
    @Column(name = "username")
    @NotBlank
    private String username;

    @Basic
    @Column(name = "password_")
    private String password;

    @Basic
    @Column(name = "teacher_num")
    private Integer teacherNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoTeacherAccount that = (PoTeacherAccount) o;
        return accountNum == that.accountNum && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(teacherNum, that.teacherNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNum, username, password, teacherNum);
    }
}
