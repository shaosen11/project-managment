package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:54 2020/3/28
 */
@Data
public class Page {
    private int pageNum;
    private int pageSize;
    private int totalRecord;
    private int totalPage;
    private List list;
    private int start;
    private int end;
    private int fromIndex;
    private int toIndex;

    public Page(int pageNum, int pageSize, int totalRecord) {
        // 当前页
        this.pageNum = pageNum;
        // 每页显示条数
        this.pageSize = pageSize;
        // 获取总条目数
        this.totalRecord = totalRecord;
        // 获取前一页
        fromIndex=(pageNum-1)*pageSize;
        toIndex=pageNum*pageSize>totalRecord?totalRecord:pageNum*pageSize;

        if (totalRecord % pageSize == 0) {
            this.totalPage = totalRecord / pageSize;
        } else {
            this.totalPage = totalRecord / pageSize + 1;
        }
        start = 1;
        end = 5;
        if (totalPage <= 5) {
            end = this.totalPage;
        } else {
            start = pageNum - 2;
            end = pageNum + 2;

            if (start < 1) {
                start = 1;
                end = 5;
            }
            if (end > this.totalPage) {
                end = totalPage;
                start = end - 5;
            }
        }
    }

}

