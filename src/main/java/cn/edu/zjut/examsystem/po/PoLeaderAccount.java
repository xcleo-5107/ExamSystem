package cn.edu.zjut.examsystem.po;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "leader_account", schema = "exam_system")
public class PoLeaderAccount {
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
    @NotBlank
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoLeaderAccount that = (PoLeaderAccount) o;
        return accountNum == that.accountNum && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNum, username, password);
    }
}
