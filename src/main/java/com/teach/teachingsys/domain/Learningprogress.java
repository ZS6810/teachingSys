package com.teach.teachingsys.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学习进度对象 learningprogress
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class Learningprogress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 进度ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 课程名称(冗余) */
    @Excel(name = "课程名称(冗余)")
    private String courseName;

    /** 章节ID */
    @Excel(name = "章节ID")
    private Long chapterId;

    /** 章节名称(冗余) */
    @Excel(name = "章节名称(冗余)")
    private String chapterName;

    /** 资料ID */
    @Excel(name = "资料ID")
    private Long materialId;

    /** 资料名称(冗余) */
    @Excel(name = "资料名称(冗余)")
    private String materialName;

    /** 资料类型(冗余) */
    @Excel(name = "资料类型(冗余)")
    private String materialType;

    /** 进度类型 */
    @Excel(name = "进度类型")
    private String progressType;

    /** 学习状态 */
    @Excel(name = "学习状态")
    private String status;

    /** 完成百分比 */
    @Excel(name = "完成百分比")
    private BigDecimal completionPercentage;

    /** 视频观看时间(秒) */
    @Excel(name = "视频观看时间(秒)")
    private Long videoWatchTime;

    /** 视频总时长(秒) */
    @Excel(name = "视频总时长(秒)")
    private Long totalVideoTime;

    /** 最后学习时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后学习时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastStudiedTime;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completedTime;

    /** 学习笔记 */
    @Excel(name = "学习笔记")
    private String notes;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }

    public void setChapterId(Long chapterId) 
    {
        this.chapterId = chapterId;
    }

    public Long getChapterId() 
    {
        return chapterId;
    }

    public void setChapterName(String chapterName) 
    {
        this.chapterName = chapterName;
    }

    public String getChapterName() 
    {
        return chapterName;
    }

    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }

    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }

    public void setMaterialType(String materialType) 
    {
        this.materialType = materialType;
    }

    public String getMaterialType() 
    {
        return materialType;
    }

    public void setProgressType(String progressType) 
    {
        this.progressType = progressType;
    }

    public String getProgressType() 
    {
        return progressType;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setCompletionPercentage(BigDecimal completionPercentage) 
    {
        this.completionPercentage = completionPercentage;
    }

    public BigDecimal getCompletionPercentage() 
    {
        return completionPercentage;
    }

    public void setVideoWatchTime(Long videoWatchTime) 
    {
        this.videoWatchTime = videoWatchTime;
    }

    public Long getVideoWatchTime() 
    {
        return videoWatchTime;
    }

    public void setTotalVideoTime(Long totalVideoTime) 
    {
        this.totalVideoTime = totalVideoTime;
    }

    public Long getTotalVideoTime() 
    {
        return totalVideoTime;
    }

    public void setLastStudiedTime(Date lastStudiedTime) 
    {
        this.lastStudiedTime = lastStudiedTime;
    }

    public Date getLastStudiedTime() 
    {
        return lastStudiedTime;
    }

    public void setCompletedTime(Date completedTime) 
    {
        this.completedTime = completedTime;
    }

    public Date getCompletedTime() 
    {
        return completedTime;
    }

    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("chapterId", getChapterId())
            .append("chapterName", getChapterName())
            .append("materialId", getMaterialId())
            .append("materialName", getMaterialName())
            .append("materialType", getMaterialType())
            .append("progressType", getProgressType())
            .append("status", getStatus())
            .append("completionPercentage", getCompletionPercentage())
            .append("videoWatchTime", getVideoWatchTime())
            .append("totalVideoTime", getTotalVideoTime())
            .append("lastStudiedTime", getLastStudiedTime())
            .append("completedTime", getCompletedTime())
            .append("notes", getNotes())
            .toString();
    }
}
