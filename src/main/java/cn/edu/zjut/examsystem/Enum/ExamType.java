package cn.edu.zjut.examsystem.Enum;


//考试安排类型
//之所以有这个枚举是因为方便后期配置,解耦
public enum ExamType {
    FinalExam(1),
    MidExam(2),
    Regular(3);

    private final  int value;
    ExamType(int value){this.value = value;}

    public int getValue() {
        return value;
    }
}