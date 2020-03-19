package cn.edu.lingnan.projectmanagment.exception;

public class AJaxResponse {
    private boolean isok;
    private int code;
    private String message;
    private Object data;

    public boolean isIsok() {
        return isok;
    }

    public void setIsok(boolean isok) {
        this.isok = isok;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AJaxResponse() {
    }

    public static AJaxResponse success(){
        AJaxResponse resultBean = new AJaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }
    public static AJaxResponse success(Object data){
        AJaxResponse resultBean = new AJaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }
    public static AJaxResponse error(CustomException e){
        AJaxResponse resultBean = new AJaxResponse();
        resultBean.setIsok(false);
        resultBean.setData("/login");
        resultBean.setCode(e.getCode());
        if (e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMessage(e.getMessage());
        }else if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMessage(e.getMessage() + ",系统出现异常");
        }else{
            resultBean.setMessage("系统出现未知异常");
        }
        return resultBean;
    }
}