package cn.edu.zjut.examsystem.Enum;

//用来设定响应代码表的,值都是事先约定的
public enum Code {
    SUCCESS(1),
    FAIL(0),
    ERROR(500);

    private final int value;

    Code(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
