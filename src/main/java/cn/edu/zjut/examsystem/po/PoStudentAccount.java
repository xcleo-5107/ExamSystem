package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "student_account", schema = "exam_system")
public class PoStudentAccount {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_num")
    private int accountNum;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password_")
    private String password;
    @Basic
    @Column(name = "student_id")
    private Integer studentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoStudentAccount that = (PoStudentAccount) o;
        return accountNum == that.accountNum && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNum, username, password, studentId);
    }
}
