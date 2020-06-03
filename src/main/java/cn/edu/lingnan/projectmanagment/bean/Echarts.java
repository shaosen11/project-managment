package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

@Data
public class Echarts {
    private String name;
    private Integer value;

    public Echarts() {
    }

    public Echarts(String name, Integer num) {
        this.name = name;
        this.value = num;
    }
}

