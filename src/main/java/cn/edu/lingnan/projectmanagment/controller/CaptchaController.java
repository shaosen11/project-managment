package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.imageCode.CaptchaCode;
import cn.edu.lingnan.projectmanagment.utils.MyContants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author shaosen
 */
@RestController
public class CaptchaController {

    @Autowired
    DefaultKaptcha captchaProducer;


    @RequestMapping(value = "/kaptcha", method = RequestMethod.GET)
    public void kaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        //禁止使用缓存图片
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, ,ust-revalidate");
        response.setHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //创建验证码文本
        String text = captchaProducer.createText();
        //把文本存进缓存
        session.setAttribute(MyContants.CAPTCHA_CODE_KEY, new CaptchaCode(text, 2 * 60));

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            //创建验证码图片
            BufferedImage image = captchaProducer.createImage(text);
            //把图片送到页面
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
        }
    }

}

