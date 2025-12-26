package com.teach.teachingsys.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程对象 course
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程ID */
    private Long id;

    /** 教师ID */
    @Excel(name = "教师ID")
    private Long teacherId;

    /** 教师姓名(冗余) */
    @Excel(name = "教师姓名(冗余)")
    private String teacherName;

    /** 教师头像URL(冗余) */
    @Excel(name = "教师头像URL(冗余)")
    private String teacherAvatar;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程描述 */
    @Excel(name = "课程描述")
    private String description;

    /** 课程价格 */
    @Excel(name = "课程价格")
    private BigDecimal price;

    /** 课程分类 */
    @Excel(name = "课程分类")
    private String category;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String coverImage;

    /** 课程状态 */
    @Excel(name = "课程状态")
    private String status;

    /** 难度等级 */
    @Excel(name = "难度等级")
    private String level;

    /** 报名学生数 */
    @Excel(name = "报名学生数")
    private Long totalStudents;

    /** 平均评分 */
    @Excel(name = "平均评分")
    private BigDecimal averageRating;

    /** 评价数量 */
    @Excel(name = "评价数量")
    private Long reviewCount;

    /** 统计信息更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计信息更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsUpdatedTime;

    /** 审核通过时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核通过时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvalTime;

    /** 拒绝原因 */
    @Excel(name = "拒绝原因")
    private String rejectionReason;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }

    public void setTeacherAvatar(String teacherAvatar) 
    {
        this.teacherAvatar = teacherAvatar;
    }

    public String getTeacherAvatar() 
    {
        return teacherAvatar;
    }

    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }

    public void setCoverImage(String coverImage) 
    {
        this.coverImage = coverImage;
    }

    public String getCoverImage() 
    {
        return coverImage;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }

    public void setTotalStudents(Long totalStudents) 
    {
        this.totalStudents = totalStudents;
    }

    public Long getTotalStudents() 
    {
        return totalStudents;
    }

    public void setAverageRating(BigDecimal averageRating) 
    {
        this.averageRating = averageRating;
    }

    public BigDecimal getAverageRating() 
    {
        return averageRating;
    }

    public void setReviewCount(Long reviewCount) 
    {
        this.reviewCount = reviewCount;
    }

    public Long getReviewCount() 
    {
        return reviewCount;
    }

    public void setStatsUpdatedTime(Date statsUpdatedTime) 
    {
        this.statsUpdatedTime = statsUpdatedTime;
    }

    public Date getStatsUpdatedTime() 
    {
        return statsUpdatedTime;
    }

    public void setApprovalTime(Date approvalTime) 
    {
        this.approvalTime = approvalTime;
    }

    public Date getApprovalTime() 
    {
        return approvalTime;
    }

    public void setRejectionReason(String rejectionReason) 
    {
        this.rejectionReason = rejectionReason;
    }

    public String getRejectionReason() 
    {
        return rejectionReason;
    }

    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }

    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("teacherAvatar", getTeacherAvatar())
            .append("courseName", getCourseName())
            .append("description", getDescription())
            .append("price", getPrice())
            .append("category", getCategory())
            .append("coverImage", getCoverImage())
            .append("status", getStatus())
            .append("level", getLevel())
            .append("totalStudents", getTotalStudents())
            .append("averageRating", getAverageRating())
            .append("reviewCount", getReviewCount())
            .append("statsUpdatedTime", getStatsUpdatedTime())
            .append("approvalTime", getApprovalTime())
            .append("rejectionReason", getRejectionReason())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
