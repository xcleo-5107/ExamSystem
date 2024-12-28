package cn.edu.zjut.examsystem.Enum;


//考试安排类型
//之所以有这个枚举是因为方便后期配置,解耦
public enum ExamReviewModel {
    TeacherExercise(1),
    TeacherAnswerSheet(2);

    private final  int value;
    ExamReviewModel(int value){this.value = value;}

    public int getValue() {
        return value;
    }
}
