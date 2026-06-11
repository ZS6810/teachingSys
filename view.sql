create definer = root@localhost view view_course_forum_complete as
select `p`.`id`                                                                                         AS `post_id`,
       `p`.`course_id`                                                                                  AS `course_id`,
       `c`.`course_name`                                                                                AS `course_name`,
       `p`.`user_id`                                                                                    AS `post_author_id`,
       `p`.`author_name`                                                                                AS `post_author_name`,
       `p`.`author_avatar`                                                                              AS `post_author_avatar`,
       `p`.`title`                                                                                      AS `post_title`,
       `p`.`content`                                                                                    AS `post_content`,
       `p`.`post_type`                                                                                  AS `post_type`,
       `p`.`status`                                                                                     AS `post_status`,
       `p`.`view_count`                                                                                 AS `post_views`,
       `p`.`reply_count`                                                                                AS `post_replies`,
       `p`.`hot_score`                                                                                  AS `post_hot_score`,
       `p`.`created_time`                                                                               AS `post_created_time`,
       `p`.`updated_time`                                                                               AS `post_updated_time`,
       `p`.`last_reply_time`                                                                            AS `last_reply_time`,
       `p`.`last_reply_author_name`                                                                     AS `last_reply_author_name`,
       (select count(0)
        from `exp4_6`.`comment`
        where ((`exp4_6`.`comment`.`post_id` = `p`.`id`) and
               (`exp4_6`.`comment`.`status` = 'normal')))                                               AS `total_comments`,
       (select count(distinct `exp4_6`.`comment`.`user_id`)
        from `exp4_6`.`comment`
        where ((`exp4_6`.`comment`.`post_id` = `p`.`id`) and
               (`exp4_6`.`comment`.`status` = 'normal')))                                               AS `unique_commenters`,
       (select max(`exp4_6`.`comment`.`created_time`)
        from `exp4_6`.`comment`
        where ((`exp4_6`.`comment`.`post_id` = `p`.`id`) and
               (`exp4_6`.`comment`.`status` = 'normal')))                                               AS `latest_comment_time`,
       (case
            when (`p`.`status` = 'pinned') then '置顶'
            when (`p`.`reply_count` > 50) then '热门'
            when (`p`.`view_count` > 1000) then '热门'
            when ((to_days(now()) - to_days(`p`.`created_time`)) <= 1) then '最新'
            else '普通' end)                                                                            AS `post_label`,
       round(((`p`.`reply_count` * 100.0) / nullif(`p`.`view_count`, 0)),
             2)                                                                                         AS `interaction_rate`,
       (select group_concat(
                       concat('[', `cm`.`user_id`, ']: ', left(`cm`.`content`, 100), ' (', `cm`.`created_time`, ')')
                           separator ' || ')
        from `exp4_6`.`comment` `cm`
        where ((`cm`.`post_id` = `p`.`id`) and (`cm`.`parent_id` is null) and (`cm`.`status` = 'normal'))
                                                                                                           limit 3)                                                                                        AS `recent_comments_preview`
from (`exp4_6`.`post` `p` join `exp4_6`.`course` `c` on ((`p`.`course_id` = `c`.`id`)))
where (`p`.`status` <> 'deleted')
order by (case when (`p`.`status` = 'pinned') then 0 else 1 end), `p`.`last_reply_time` desc, `p`.`created_time` desc;

-- comment on column view_course_forum_complete.post_id not supported: 帖子ID

-- comment on column view_course_forum_complete.course_id not supported: 课程ID

-- comment on column view_course_forum_complete.course_name not supported: 课程名称

-- comment on column view_course_forum_complete.post_author_id not supported: 发帖用户ID

-- comment on column view_course_forum_complete.post_author_name not supported: 发帖人姓名(冗余)

-- comment on column view_course_forum_complete.post_author_avatar not supported: 发帖人头像URL(冗余)

-- comment on column view_course_forum_complete.post_title not supported: 帖子标题

-- comment on column view_course_forum_complete.post_content not supported: 帖子内容

-- comment on column view_course_forum_complete.post_type not supported: 帖子类型

-- comment on column view_course_forum_complete.post_status not supported: 帖子状态

-- comment on column view_course_forum_complete.post_views not supported: 查看次数

-- comment on column view_course_forum_complete.post_replies not supported: 回复次数

-- comment on column view_course_forum_complete.post_hot_score not supported: 热度分数(计算字段)

-- comment on column view_course_forum_complete.post_created_time not supported: 创建时间

-- comment on column view_course_forum_complete.post_updated_time not supported: 更新时间

-- comment on column view_course_forum_complete.last_reply_time not supported: 最后回复时间

-- comment on column view_course_forum_complete.last_reply_author_name not supported: 最后回复人姓名

create definer = root@localhost view view_course_statistics as
select `c`.`id`                               AS `id`,
       `c`.`teacher_id`                       AS `teacher_id`,
       `c`.`teacher_name`                     AS `teacher_name`,
       `c`.`teacher_avatar`                   AS `teacher_avatar`,
       `c`.`course_name`                      AS `course_name`,
       `c`.`description`                      AS `description`,
       `c`.`price`                            AS `price`,
       `c`.`category`                         AS `category`,
       `c`.`cover_image`                      AS `cover_image`,
       `c`.`status`                           AS `status`,
       `c`.`level`                            AS `level`,
       `c`.`total_students`                   AS `total_students`,
       `c`.`average_rating`                   AS `average_rating`,
       `c`.`review_count`                     AS `review_count`,
       `c`.`stats_updated_time`               AS `stats_updated_time`,
       `c`.`approval_time`                    AS `approval_time`,
       `c`.`rejection_reason`                 AS `rejection_reason`,
       `c`.`created_time`                     AS `created_time`,
       `c`.`updated_time`                     AS `updated_time`,
       count(distinct `uc`.`user_id`)         AS `enrolled_students_count`,
       count(distinct `ch`.`id`)              AS `chapters_count`,
       count(distinct `cm`.`id`)              AS `materials_count`,
       count(distinct `p`.`id`)               AS `posts_count`,
       count(distinct `a`.`id`)               AS `assignments_count`,
       coalesce(avg(`uc`.`progress_rate`), 0) AS `avg_progress_rate`
from (((((`exp4_6`.`course` `c` left join `exp4_6`.`user_course` `uc`
          on (((`c`.`id` = `uc`.`course_id`) and (`uc`.`status` = 'active')))) left join `exp4_6`.`chapter` `ch`
         on ((`c`.`id` = `ch`.`course_id`))) left join `exp4_6`.`coursematerial` `cm`
        on ((`ch`.`id` = `cm`.`chapter_id`))) left join `exp4_6`.`post` `p`
       on (((`c`.`id` = `p`.`course_id`) and (`p`.`status` <> 'deleted')))) left join `exp4_6`.`assignment` `a`
      on ((`c`.`id` = `a`.`course_id`)))
group by `c`.`id`;

-- comment on column view_course_statistics.id not supported: 课程ID

-- comment on column view_course_statistics.teacher_id not supported: 教师ID

-- comment on column view_course_statistics.teacher_name not supported: 教师姓名(冗余)

-- comment on column view_course_statistics.teacher_avatar not supported: 教师头像URL(冗余)

-- comment on column view_course_statistics.course_name not supported: 课程名称

-- comment on column view_course_statistics.description not supported: 课程描述

-- comment on column view_course_statistics.price not supported: 课程价格

-- comment on column view_course_statistics.category not supported: 课程分类

-- comment on column view_course_statistics.cover_image not supported: 封面图片

-- comment on column view_course_statistics.status not supported: 课程状态

-- comment on column view_course_statistics.level not supported: 难度等级

-- comment on column view_course_statistics.total_students not supported: 报名学生数

-- comment on column view_course_statistics.average_rating not supported: 平均评分

-- comment on column view_course_statistics.review_count not supported: 评价数量

-- comment on column view_course_statistics.stats_updated_time not supported: 统计信息更新时间

-- comment on column view_course_statistics.approval_time not supported: 审核通过时间

-- comment on column view_course_statistics.rejection_reason not supported: 拒绝原因

-- comment on column view_course_statistics.created_time not supported: 创建时间

-- comment on column view_course_statistics.updated_time not supported: 更新时间

create definer = root@localhost view view_course_students as
select `c`.`id`                                                  AS `course_id`,
       `c`.`course_name`                                         AS `course_name`,
       `c`.`teacher_id`                                          AS `teacher_id`,
       `t`.`real_name`                                           AS `teacher_name`,
       `u`.`id`                                                  AS `student_id`,
       `u`.`username`                                            AS `student_username`,
       `u`.`real_name`                                           AS `student_real_name`,
       `u`.`email`                                               AS `student_email`,
       `u`.`avatar`                                              AS `student_avatar`,
       `u`.`user_type`                                           AS `student_type`,
       `u`.`status`                                              AS `student_status`,
       `u`.`phone`                                               AS `student_phone`,
       `uc`.`enrolled_time`                                      AS `enrolled_time`,
       `uc`.`progress_rate`                                      AS `progress_rate`,
       `uc`.`completed_chapters`                                 AS `completed_chapters`,
       `uc`.`total_chapters`                                     AS `total_chapters`,
       `uc`.`completed_materials`                                AS `completed_materials`,
       `uc`.`total_materials`                                    AS `total_materials`,
       `uc`.`status`                                             AS `enrollment_status`,
       `uc`.`last_accessed`                                      AS `last_accessed`,
       `uc`.`completion_time`                                    AS `completion_time`,
       (to_days(now()) - to_days(`uc`.`enrolled_time`))          AS `days_enrolled`,
       (to_days(now()) - to_days(`uc`.`last_accessed`))          AS `days_since_last_access`,
       (case
            when ((to_days(now()) - to_days(`uc`.`enrolled_time`)) > 0) then round(
                    (`uc`.`progress_rate` / (to_days(now()) - to_days(`uc`.`enrolled_time`))), 2)
            else 0 end)                                          AS `daily_progress_rate`,
       (case
            when (`uc`.`progress_rate` = 0) then '未开始'
            when ((`uc`.`progress_rate` > 0) and (`uc`.`progress_rate` < 30)) then '刚开始'
            when ((`uc`.`progress_rate` >= 30) and (`uc`.`progress_rate` < 70)) then '进行中'
            when ((`uc`.`progress_rate` >= 70) and (`uc`.`progress_rate` < 100)) then '即将完成'
            when (`uc`.`progress_rate` = 100) then '已完成' end) AS `learning_phase`
from (((`exp4_6`.`course` `c` join `exp4_6`.`user` `t`
        on ((`c`.`teacher_id` = `t`.`id`))) join `exp4_6`.`user_course` `uc`
       on ((`c`.`id` = `uc`.`course_id`))) join `exp4_6`.`user` `u` on ((`uc`.`user_id` = `u`.`id`)))
where ((`u`.`user_type` = 'student') and (`c`.`status` = 'published'))
order by `c`.`id`, `uc`.`enrolled_time` desc;

-- comment on column view_course_students.course_id not supported: 课程ID

-- comment on column view_course_students.course_name not supported: 课程名称

-- comment on column view_course_students.teacher_id not supported: 教师ID

-- comment on column view_course_students.teacher_name not supported: 真实姓名

-- comment on column view_course_students.student_id not supported: 用户ID

-- comment on column view_course_students.student_username not supported: 用户名

-- comment on column view_course_students.student_real_name not supported: 真实姓名

-- comment on column view_course_students.student_email not supported: 邮箱

-- comment on column view_course_students.student_avatar not supported: 头像URL

-- comment on column view_course_students.student_type not supported: 用户类型

-- comment on column view_course_students.student_status not supported: 状态

-- comment on column view_course_students.student_phone not supported: 手机号

-- comment on column view_course_students.enrolled_time not supported: 报名时间

-- comment on column view_course_students.progress_rate not supported: 学习进度百分比

-- comment on column view_course_students.completed_chapters not supported: 已完成章节数

-- comment on column view_course_students.total_chapters not supported: 总章节数

-- comment on column view_course_students.completed_materials not supported: 已完成资料数

-- comment on column view_course_students.total_materials not supported: 总资料数

-- comment on column view_course_students.enrollment_status not supported: 学习状态

-- comment on column view_course_students.last_accessed not supported: 最后访问时间

-- comment on column view_course_students.completion_time not supported: 完成时间

create definer = root@localhost view view_question_detail as
select `q`.`id`                    AS `question_id`,
       `q`.`question_type`         AS `question_type`,
       `q`.`question_text`         AS `question_text`,
       `q`.`difficulty`            AS `difficulty`,
       `q`.`score`                 AS `score`,
       `q`.`explanation`           AS `explanation`,
       `q`.`tags`                  AS `tags`,
       `q`.`created_time`          AS `created_time`,
       `q`.`updated_time`          AS `updated_time`,
       `cq`.`id`                   AS `choice_question_id`,
       `cq`.`option_a`             AS `option_a`,
       `cq`.`option_b`             AS `option_b`,
       `cq`.`option_c`             AS `option_c`,
       `cq`.`option_d`             AS `option_d`,
       `cq`.`option_e`             AS `option_e`,
       `cq`.`option_f`             AS `option_f`,
       `cq`.`correct_answer`       AS `correct_answer`,
       `cq`.`is_multiple`          AS `is_multiple`,
       `saq`.`id`                  AS `short_answer_question_id`,
       `saq`.`reference_answer`    AS `reference_answer`,
       `saq`.`answer_length_limit` AS `answer_length_limit`
from ((`exp4_6`.`questionbank` `q` left join `exp4_6`.`choice_question` `cq`
       on (((`q`.`id` = `cq`.`question_id`) and (`q`.`question_type` = 'choice')))) left join `exp4_6`.`short_answer_question` `saq`
      on (((`q`.`id` = `saq`.`question_id`) and (`q`.`question_type` = 'short_answer'))));

-- comment on column view_question_detail.question_id not supported: 题目ID

-- comment on column view_question_detail.question_type not supported: 题目类型

-- comment on column view_question_detail.question_text not supported: 题目内容

-- comment on column view_question_detail.difficulty not supported: 难度

-- comment on column view_question_detail.score not supported: 分值

-- comment on column view_question_detail.explanation not supported: 答案解析

-- comment on column view_question_detail.tags not supported: 标签

-- comment on column view_question_detail.created_time not supported: 创建时间

-- comment on column view_question_detail.updated_time not supported: 更新时间

-- comment on column view_question_detail.choice_question_id not supported: 选择题ID

-- comment on column view_question_detail.option_a not supported: 选项A

-- comment on column view_question_detail.option_b not supported: 选项B

-- comment on column view_question_detail.option_c not supported: 选项C

-- comment on column view_question_detail.option_d not supported: 选项D

-- comment on column view_question_detail.option_e not supported: 选项E

-- comment on column view_question_detail.option_f not supported: 选项F

-- comment on column view_question_detail.correct_answer not supported: 正确答案(如A,B,AB等)

-- comment on column view_question_detail.is_multiple not supported: 是否多选题

-- comment on column view_question_detail.short_answer_question_id not supported: 简答题ID

-- comment on column view_question_detail.reference_answer not supported: 参考答案

-- comment on column view_question_detail.answer_length_limit not supported: 答案长度限制

create definer = root@localhost view view_user_role as
select `exp4_6`.`user`.`id`        AS `id`,
       `exp4_6`.`user`.`username`  AS `username`,
       `exp4_6`.`role`.`role_name` AS `role_name`
from ((`exp4_6`.`role` join `exp4_6`.`user`) join `exp4_6`.`user_role`)
where ((`exp4_6`.`role`.`id` = `exp4_6`.`user_role`.`role_id`) and
       (`exp4_6`.`user`.`id` = `exp4_6`.`user_role`.`user_id`));

-- comment on column view_user_role.id not supported: 用户ID

-- comment on column view_user_role.username not supported: 用户名

-- comment on column view_user_role.role_name not supported: 角色名称

