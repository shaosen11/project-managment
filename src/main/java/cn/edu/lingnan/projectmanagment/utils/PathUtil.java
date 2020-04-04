package cn.edu.lingnan.projectmanagment.utils;

import java.util.Map;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:32 2020/4/4
 */
public class PathUtil {
    public static final String pathUtil(Map<String,Object> map){
        String path = "";
        Integer count = 0;
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            if(count == 0){
                path += "?";
            }else {
                path += "&";
            }
            path += entry.getKey() + "=" + entry.getValue();
            count++;
        }
        return path;
    }
}
