package cn.edu.zjut.examsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCreateExamModule {
    private int moduleInExamNum;    //大题题号
    private int exerciseType;   //大题类型
    private int exerciseNumber; //大题题目数量
    private int moduleTotalScore;   //大题分数占比
}
