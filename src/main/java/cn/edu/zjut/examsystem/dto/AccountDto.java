package cn.edu.zjut.examsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountDto {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
}
