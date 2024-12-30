package cn.edu.zjut.examsystem.dto;

import cn.edu.zjut.examsystem.po.PoCourse;
import cn.edu.zjut.examsystem.po.PoExamModule;
import cn.edu.zjut.examsystem.po.PoTeacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AutoCreateExamDto {

    @NotBlank
    private Byte isReleased; //是否发布

    @NotBlank
    private int teacherNum; //发布者id

    @NotBlank
    private int courseNum;    //课程编号

    private List<AutoCreateExamModule> modules; //大题模块的相关设置

    @NotBlank
    private int exerciseDifficultyAvg;    //平均难度

}
