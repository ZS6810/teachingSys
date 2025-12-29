package com.teach.teachingsys.graph.repository;

/**
 * 改为普通类（DTO），强制接收查询结果
 * 这样 Spring 就不会把它当成 GraphStudent 实体去处理了
 */
public class GraphCourseRecommendationView {

    private Long id;
    private String name;
    private Long frequency;

    // 必须提供 Getter 和 Setter 方法

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }
}