package cn.edu.zjut.examsystem.Enum;

//登录状态
public enum LoginStatus {
    UserNameOrPasswordFail(-3), //用户名密码错误
    AccountHaveNoEntity(-2),    //用户未绑定实体
    EntityNotFound(-1); //用户绑定的实体不存在

    private final int value;

    LoginStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
