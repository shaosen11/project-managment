package cn.edu.lingnan.projectmanagment.imageCode;


import cn.edu.lingnan.projectmanagment.security.MyAuthenticationFailureHandler;
import cn.edu.lingnan.projectmanagment.utils.MyContants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author shaosen
 */
@Component
public class CaptchaCodeFilter extends OncePerRequestFilter {

    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if(StringUtils.equals("/login_user", httpServletRequest.getRequestURI())
            && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")){
            //验证谜底与用户输入是否匹配
            try {
                //验证码谜底与用户输入是否匹配
                validate(new ServletWebRequest(httpServletRequest));
            }catch (AuthenticationException e){
                myAuthenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }

        }
        //继续做后面的验证
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        HttpSession session = request.getRequest().getSession();

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "captchaCode");
        System.out.println("验证码：" + codeInRequest);
        //判断验证码是否为空
        if (StringUtils.isEmpty(codeInRequest)) {
            throw new SessionAuthenticationException("验证码不能为空");
        }

        //获取服务器session池中的验证码
        CaptchaCode codeInSession = (CaptchaCode) session.getAttribute(MyContants.CAPTCHA_CODE_KEY);
        if (Objects.isNull(codeInSession)) {
            throw new SessionAuthenticationException("验证码不存在");
        }

        //校验服务器session池中的验证码是否过期
        if (codeInSession.isExpired()) {
            session.removeAttribute(MyContants.CAPTCHA_CODE_KEY);
            throw new SessionAuthenticationException("验证码已经过期");
        }

        //请求验证码校验
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new SessionAuthenticationException("验证码不匹配");
        }
    }
}
