package com.teach.teachingsys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程资料对象 coursematerial
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class Coursematerial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资料ID */
    private Long id;

    /** 章节ID */
    @Excel(name = "章节ID")
    private Long chapterId;

    /** 资料名称 */
    @Excel(name = "资料名称")
    private String materialName;

    /** 资料类型 */
    @Excel(name = "资料类型")
    private String materialType;

    /** 文件URL */
    @Excel(name = "文件URL")
    private String fileUrl;

    /** 文件大小(字节) */
    @Excel(name = "文件大小(字节)")
    private Long fileSize;

    /** 视频时长(秒) */
    @Excel(name = "视频时长(秒)")
    private Long duration;

    /** 资料描述 */
    @Excel(name = "资料描述")
    private String description;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private Long downloadCount;

    /** 观看次数 */
    @Excel(name = "观看次数")
    private Long viewCount;

    /** 是否免费 */
    @Excel(name = "是否免费")
    private Integer isFree;

    /** 资料顺序 */
    @Excel(name = "资料顺序")
    private Long materialOrder;

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

    public void setChapterId(Long chapterId) 
    {
        this.chapterId = chapterId;
    }

    public Long getChapterId() 
    {
        return chapterId;
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

    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }

    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
    }

    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDownloadCount(Long downloadCount) 
    {
        this.downloadCount = downloadCount;
    }

    public Long getDownloadCount() 
    {
        return downloadCount;
    }

    public void setViewCount(Long viewCount) 
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount() 
    {
        return viewCount;
    }

    public void setIsFree(Integer isFree) 
    {
        this.isFree = isFree;
    }

    public Integer getIsFree() 
    {
        return isFree;
    }

    public void setMaterialOrder(Long materialOrder) 
    {
        this.materialOrder = materialOrder;
    }

    public Long getMaterialOrder() 
    {
        return materialOrder;
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
            .append("chapterId", getChapterId())
            .append("materialName", getMaterialName())
            .append("materialType", getMaterialType())
            .append("fileUrl", getFileUrl())
            .append("fileSize", getFileSize())
            .append("duration", getDuration())
            .append("description", getDescription())
            .append("downloadCount", getDownloadCount())
            .append("viewCount", getViewCount())
            .append("isFree", getIsFree())
            .append("materialOrder", getMaterialOrder())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
