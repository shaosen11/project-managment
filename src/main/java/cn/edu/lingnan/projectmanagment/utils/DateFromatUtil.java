package cn.edu.lingnan.projectmanagment.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 0:17 2020/4/5
 */
public class DateFromatUtil {
    public static String getNowDateShort(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
