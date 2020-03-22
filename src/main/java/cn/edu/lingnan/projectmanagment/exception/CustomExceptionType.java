package cn.edu.lingnan.projectmanagment.exception;

/**
 * @author shaosen
 */
public enum  CustomExceptionType {
    USER_INPUT_ERROR(400,"用户输入异常"),
    SYSTEM_ERROR(500,"系统服务异常"),
    OTHER_ERROR(999,"其他未知异常");

    private int code;
    private String typeDesc;

    CustomExceptionType(int code, String typeDesc) {
        this.code = code;
        this.typeDesc = typeDesc;
    }

    public int getCode() {
        return code;
    }

    public String getTypeDesc() {
        return typeDesc;
    }
}