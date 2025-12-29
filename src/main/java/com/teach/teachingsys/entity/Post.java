package com.teach.teachingsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teach.teachingsys.entity.enums.ForumEnums.PostStatus;
import com.teach.teachingsys.entity.enums.ForumEnums.PostType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "post")
@SQLDelete(sql = "UPDATE post SET is_deleted = 1, deleted_time = NOW() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "post_ibfk_1"))
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "post_ibfk_2"))
    private User author;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_avatar")
    private String authorAvatar;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType postType = PostType.discussion;

    @Enumerated(EnumType.STRING)
    private PostStatus status = PostStatus.normal;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "reply_count")
    private Integer replyCount = 0;

    @Column(name = "hot_score")
    private Integer hotScore = 0;

    @Column(name = "stats_updated_time")
    private LocalDateTime statsUpdatedTime;

    @Column(name = "last_reply_time")
    private LocalDateTime lastReplyTime;

    @Column(name = "last_reply_author_name")
    private String lastReplyAuthorName;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}

