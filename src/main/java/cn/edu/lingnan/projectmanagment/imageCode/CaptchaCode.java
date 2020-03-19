package cn.edu.lingnan.projectmanagment.imageCode;

import java.time.LocalDateTime;

public class CaptchaCode {
    private String code;
    private LocalDateTime expireTime;
    //设置验证码过期时间
    public CaptchaCode(String code, int expireAfterSeconds){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public String getCode() {
        return code;
    }
    //判断验证码是否过期
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
