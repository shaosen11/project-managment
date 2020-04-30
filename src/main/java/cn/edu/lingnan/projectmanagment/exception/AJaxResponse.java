package cn.edu.lingnan.projectmanagment.exception;

import lombok.Data;

/**
 * @author shaosen
 */
@Data
public class AJaxResponse {
    private boolean isOk;
    private int code;
    private String message;
    private Object data;


    public AJaxResponse() {
    }

    public static AJaxResponse success(){
        AJaxResponse resultBean = new AJaxResponse();
        resultBean.setOk(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }
    public static AJaxResponse success(Object data){
        AJaxResponse resultBean = new AJaxResponse();
        resultBean.setOk(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }
    public static AJaxResponse success(String msg){
        AJaxResponse resultBean = new AJaxResponse();
        resultBean.setOk(true);
        resultBean.setCode(200);
        resultBean.setMessage(msg);
        return resultBean;
    }
    public static AJaxResponse success(Object data, String msg){
        AJaxResponse resultBean = new AJaxResponse();
        resultBean.setOk(true);
        resultBean.setCode(200);
        resultBean.setMessage(msg);
        resultBean.setData(data);
        return resultBean;
    }
    public static AJaxResponse error(CustomException e){
        AJaxResponse resultBean = new AJaxResponse();
        resultBean.setOk(false);
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