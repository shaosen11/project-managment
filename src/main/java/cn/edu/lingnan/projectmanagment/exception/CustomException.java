package cn.edu.lingnan.projectmanagment.exception;

public class CustomException {
    private int code;
    private String message;
    private CustomExceptionType customExceptionType;

    public CustomException(CustomExceptionType customExceptionType, String message) {
        this.message = message;
        this.customExceptionType = customExceptionType;
        this.code = customExceptionType.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomExceptionType getCustomExceptionType() {
        return customExceptionType;
    }

    public void setCustomExceptionType(CustomExceptionType customExceptionType) {
        this.customExceptionType = customExceptionType;
    }

}