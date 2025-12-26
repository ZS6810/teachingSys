create table questionbank
(
    id            bigint auto_increment comment '题目ID'
        primary key,
    question_type enum ('choice', 'short_answer')                           not null comment '题目类型',
    question_text text                                                      not null comment '题目内容',
    difficulty    enum ('easy', 'medium', 'hard') default 'medium'          null comment '难度',
    score         decimal(5, 2)                   default 1.00              null comment '分值',
    explanation   text                                                      null comment '答案解析',
    tags          json                                                      null comment '标签',
    created_time  datetime                        default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time  datetime                        default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '题库表';

create table choice_question
(
    id             bigint auto_increment comment '选择题ID'
        primary key,
    question_id    bigint               not null comment '题目ID',
    option_a       text                 null comment '选项A',
    option_b       text                 null comment '选项B',
    option_c       text                 null comment '选项C',
    option_d       text                 null comment '选项D',
    option_e       text                 null comment '选项E',
    option_f       text                 null comment '选项F',
    correct_answer varchar(10)          not null comment '正确答案(如A,B,AB等)',
    is_multiple    tinyint(1) default 0 null comment '是否多选题',
    constraint uk_question
        unique (question_id),
    constraint choice_question_ibfk_1
        foreign key (question_id) references questionbank (id)
            on delete cascade
)
    comment '选择题表';

create table role
(
    id           bigint auto_increment comment '角色ID'
        primary key,
    role_name    varchar(50)                        not null comment '角色名称',
    role_code    varchar(50)                        not null comment '角色代码',
    description  text                               null comment '角色描述',
    permissions  json                               null comment '权限列表',
    created_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    constraint role_code
        unique (role_code),
    constraint role_name
        unique (role_name)
)
    comment '角色表';

create table short_answer_question
(
    id                  bigint auto_increment comment '简答题ID'
        primary key,
    question_id         bigint not null comment '题目ID',
    reference_answer    text   null comment '参考答案',
    answer_length_limit int    null comment '答案长度限制',
    constraint uk_question
        unique (question_id),
    constraint short_answer_question_ibfk_1
        foreign key (question_id) references questionbank (id)
            on delete cascade
)
    comment '简答题表';

create table user
(
    id                  bigint auto_increment comment '用户ID'
        primary key,
    username            varchar(50)                                                      not null comment '用户名',
    password            varchar(255)                                                     not null comment '密码',
    email               varchar(100)                                                     not null comment '邮箱',
    real_name           varchar(100)                                                     not null comment '真实姓名',
    phone               varchar(20)                                                      null comment '手机号',
    avatar              varchar(255)                                                     null comment '头像URL',
    user_type           enum ('student', 'teacher', 'admin')                             not null comment '用户类型',
    status              enum ('active', 'inactive', 'pending') default 'active'          null comment '状态',
    qualification_proof varchar(255)                                                     null comment '教师资质证明文件路径',
    last_login_time     datetime                                                         null comment '最后登录时间',
    created_time        datetime                               default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time        datetime                               default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint email
        unique (email),
    constraint username
        unique (username)
)
    comment '用户表';

create table course
(
    id                 bigint auto_increment comment '课程ID'
        primary key,
    teacher_id         bigint                                                                                   not null comment '教师ID',
    teacher_name       varchar(100)                                                                             null comment '教师姓名(冗余)',
    teacher_avatar     varchar(255)                                                                             null comment '教师头像URL(冗余)',
    course_name        varchar(200)                                                                             not null comment '课程名称',
    description        text                                                                                     null comment '课程描述',
    price              decimal(10, 2)                                                 default 0.00              null comment '课程价格',
    category           varchar(100)                                                                             null comment '课程分类',
    cover_image        varchar(255)                                                                             null comment '封面图片',
    status             enum ('draft', 'pending', 'approved', 'rejected', 'published') default 'draft'           null comment '课程状态',
    level              enum ('beginner', 'intermediate', 'advanced')                                            null comment '难度等级',
    total_students     int                                                            default 0                 null comment '报名学生数',
    average_rating     decimal(3, 2)                                                  default 0.00              null comment '平均评分',
    review_count       int                                                            default 0                 null comment '评价数量',
    stats_updated_time datetime                                                       default CURRENT_TIMESTAMP null comment '统计信息更新时间',
    approval_time      datetime                                                                                 null comment '审核通过时间',
    rejection_reason   text                                                                                     null comment '拒绝原因',
    created_time       datetime                                                       default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time       datetime                                                       default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint course_ibfk_1
        foreign key (teacher_id) references user (id)
            on delete cascade
)
    comment '课程表';

create table assignment
(
    id                 bigint auto_increment comment '作业ID'
        primary key,
    course_id          bigint                                                          not null comment '课程ID',
    title              varchar(200)                                                    not null comment '作业标题',
    description        text                                                            null comment '作业描述',
    assignment_type    enum ('homework', 'exam')                                       not null comment '作业类型',
    start_time         datetime                                                        null comment '开始时间',
    deadline           datetime                                                        null comment '截止时间',
    total_score        decimal(5, 2)                         default 100.00            null comment '总分',
    passing_score      decimal(5, 2)                         default 60.00             null comment '及格分数',
    time_limit         int                                                             null comment '时间限制(分钟)',
    max_attempts       int                                   default 1                 null comment '最大尝试次数',
    submission_count   int                                   default 0                 null comment '提交次数',
    graded_count       int                                   default 0                 null comment '已批改次数',
    average_score      decimal(5, 2)                         default 0.00              null comment '平均分',
    highest_score      decimal(5, 2)                         default 0.00              null comment '最高分',
    lowest_score       decimal(5, 2)                         default 0.00              null comment '最低分',
    stats_updated_time datetime                              default CURRENT_TIMESTAMP null comment '统计信息更新时间',
    status             enum ('draft', 'published', 'closed') default 'draft'           null comment '状态',
    created_time       datetime                              default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time       datetime                              default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint assignment_ibfk_1
        foreign key (course_id) references course (id)
            on delete cascade
)
    comment '作业表';

create index idx_assignment_course_status
    on assignment (course_id, status);

create index idx_assignment_deadline
    on assignment (deadline);

create table assignment_bank
(
    id             bigint auto_increment comment '关系ID'
        primary key,
    assignment_id  bigint not null comment '作业ID',
    question_id    bigint not null comment '题目ID',
    question_order int    null comment '题目顺序',
    constraint uk_assignment_question
        unique (assignment_id, question_id),
    constraint assignment_bank_ibfk_1
        foreign key (assignment_id) references assignment (id)
            on delete cascade,
    constraint assignment_bank_ibfk_2
        foreign key (question_id) references questionbank (id)
            on delete cascade
)
    comment '作业题库关系表';

create index idx_assignmentbank_assignment
    on assignment_bank (assignment_id);

create index idx_assignmentbank_order
    on assignment_bank (assignment_id, question_order);

create index question_id
    on assignment_bank (question_id);

create table assignment_submission
(
    id                    bigint auto_increment comment '提交ID'
        primary key,
    assignment_id         bigint                                                             not null comment '作业ID',
    user_id               bigint                                                             not null comment '学生ID',
    attempt_number        int                                      default 1                 null comment '尝试次数',
    submitted_time        datetime                                 default CURRENT_TIMESTAMP null comment '提交时间',
    total_score           decimal(5, 2)                            default 0.00              null comment '得分',
    auto_graded_score     decimal(5, 2)                            default 0.00              null comment '自动批改分数',
    teacher_feedback      text                                                               null comment '教师反馈',
    graded_time           datetime                                                           null comment '批改时间',
    grader_id             bigint                                                             null comment '批改教师ID',
    submission_data       json                                                               null comment '提交内容',
    has_question_snapshot tinyint(1)                               default 0                 null comment '是否包含题目快照',
    status                enum ('submitted', 'graded', 'returned') default 'submitted'       null comment '提交状态',
    constraint uk_assignment_user_attempt
        unique (assignment_id, user_id, attempt_number),
    constraint assignment_submission_ibfk_1
        foreign key (assignment_id) references assignment (id)
            on delete cascade,
    constraint assignment_submission_ibfk_2
        foreign key (user_id) references user (id)
            on delete cascade,
    constraint assignment_submission_ibfk_3
        foreign key (grader_id) references user (id)
            on delete set null
)
    comment '作业提交表';

create index grader_id
    on assignment_submission (grader_id);

create index idx_submission_assignment_user
    on assignment_submission (assignment_id, user_id);

create index idx_submission_submitted_time
    on assignment_submission (submitted_time desc);

create index user_id
    on assignment_submission (user_id);

create table chapter
(
    id            bigint auto_increment comment '章节ID'
        primary key,
    course_id     bigint                               not null comment '课程ID',
    chapter_name  varchar(200)                         not null comment '章节名称',
    chapter_order int                                  not null comment '章节顺序',
    description   text                                 null comment '章节描述',
    parent_id     bigint                               null comment '父章节ID',
    is_public     tinyint(1) default 1                 null comment '是否公开',
    created_time  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint chapter_ibfk_1
        foreign key (course_id) references course (id)
            on delete cascade,
    constraint chapter_ibfk_2
        foreign key (parent_id) references chapter (id)
            on delete cascade
)
    comment '章节表';

create index idx_chapter_course
    on chapter (course_id);

create index idx_chapter_course_order
    on chapter (course_id, chapter_order);

create index parent_id
    on chapter (parent_id);

create index teacher_id
    on course (teacher_id);

create table coursematerial
(
    id             bigint auto_increment comment '资料ID'
        primary key,
    chapter_id     bigint                                      not null comment '章节ID',
    material_name  varchar(200)                                not null comment '资料名称',
    material_type  enum ('video', 'ppt', 'pdf', 'doc', 'text') not null comment '资料类型',
    file_url       varchar(500)                                not null comment '文件URL',
    file_size      bigint                                      null comment '文件大小(字节)',
    duration       int                                         null comment '视频时长(秒)',
    description    text                                        null comment '资料描述',
    download_count int        default 0                        null comment '下载次数',
    view_count     int        default 0                        null comment '观看次数',
    is_free        tinyint(1) default 0                        null comment '是否免费',
    material_order int                                         null comment '资料顺序',
    created_time   datetime   default CURRENT_TIMESTAMP        null comment '创建时间',
    updated_time   datetime   default CURRENT_TIMESTAMP        null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint coursematerial_ibfk_1
        foreign key (chapter_id) references chapter (id)
            on delete cascade
)
    comment '课程资料表';

create index idx_coursematerial_chapter
    on coursematerial (chapter_id);

create index idx_coursematerial_order
    on coursematerial (chapter_id, material_order);

create table exam
(
    id                  bigint auto_increment comment '考试ID'
        primary key,
    assignment_id       bigint               not null comment '作业ID',
    shuffle_questions   tinyint(1) default 1 null comment '是否打乱题目顺序',
    show_correct_answer tinyint(1) default 0 null comment '是否显示正确答案',
    allow_review        tinyint(1) default 1 null comment '是否允许回顾',
    settings            json                 null comment '考试设置',
    constraint uk_assignment
        unique (assignment_id),
    constraint exam_ibfk_1
        foreign key (assignment_id) references assignment (id)
            on delete cascade
)
    comment '考试表';

create table exam_bank
(
    id             bigint auto_increment comment '关系ID'
        primary key,
    exam_id        bigint not null comment '考试ID',
    question_id    bigint not null comment '题目ID',
    question_order int    null comment '题目顺序',
    constraint uk_exam_question
        unique (exam_id, question_id),
    constraint exam_bank_ibfk_1
        foreign key (exam_id) references exam (id)
            on delete cascade,
    constraint exam_bank_ibfk_2
        foreign key (question_id) references questionbank (id)
            on delete cascade
)
    comment '考试题库关系表';

create index question_id
    on exam_bank (question_id);

create table learningprogress
(
    id                    bigint auto_increment comment '进度ID'
        primary key,
    user_id               bigint                                                                 not null comment '用户ID',
    course_id             bigint                                                                 not null comment '课程ID',
    course_name           varchar(200)                                                           null comment '课程名称(冗余)',
    chapter_id            bigint                                                                 null comment '章节ID',
    chapter_name          varchar(200)                                                           null comment '章节名称(冗余)',
    material_id           bigint                                                                 null comment '资料ID',
    material_name         varchar(200)                                                           null comment '资料名称(冗余)',
    material_type         enum ('video', 'ppt', 'pdf', 'doc', 'text')                            null comment '资料类型(冗余)',
    progress_type         enum ('chapter', 'material')                                           not null comment '进度类型',
    status                enum ('not_started', 'in_progress', 'completed') default 'not_started' null comment '学习状态',
    completion_percentage decimal(5, 2)                                    default 0.00          null comment '完成百分比',
    video_watch_time      int                                              default 0             null comment '视频观看时间(秒)',
    total_video_time      int                                              default 0             null comment '视频总时长(秒)',
    last_studied_time     datetime                                                               null comment '最后学习时间',
    completed_time        datetime                                                               null comment '完成时间',
    notes                 text                                                                   null comment '学习笔记',
    constraint uk_user_chapter
        unique (user_id, chapter_id),
    constraint uk_user_material
        unique (user_id, material_id),
    constraint learningprogress_ibfk_1
        foreign key (user_id) references user (id)
            on delete cascade,
    constraint learningprogress_ibfk_2
        foreign key (course_id) references course (id)
            on delete cascade,
    constraint learningprogress_ibfk_3
        foreign key (chapter_id) references chapter (id)
            on delete set null,
    constraint learningprogress_ibfk_4
        foreign key (material_id) references coursematerial (id)
            on delete set null
)
    comment '学习进度表';

create index chapter_id
    on learningprogress (chapter_id);

create index course_id
    on learningprogress (course_id);

create index idx_learningprogress_user_course
    on learningprogress (user_id, course_id);

create index idx_learningprogress_user_material
    on learningprogress (user_id, material_id);

create index material_id
    on learningprogress (material_id);

create table post
(
    id                     bigint auto_increment comment '帖子ID'
        primary key,
    course_id              bigint                                                                    not null comment '课程ID',
    user_id                bigint                                                                    not null comment '发帖用户ID',
    author_name            varchar(100)                                                              null comment '发帖人姓名(冗余)',
    author_avatar          varchar(255)                                                              null comment '发帖人头像URL(冗余)',
    title                  varchar(200)                                                              not null comment '帖子标题',
    content                text                                                                      not null comment '帖子内容',
    post_type              enum ('discussion', 'question', 'announcement') default 'discussion'      null comment '帖子类型',
    status                 enum ('normal', 'pinned', 'deleted')            default 'normal'          null comment '帖子状态',
    view_count             int                                             default 0                 null comment '查看次数',
    reply_count            int                                             default 0                 null comment '回复次数',
    hot_score              int                                             default 0                 null comment '热度分数(计算字段)',
    stats_updated_time     datetime                                        default CURRENT_TIMESTAMP null comment '统计信息更新时间',
    last_reply_time        datetime                                                                  null comment '最后回复时间',
    last_reply_author_name varchar(100)                                                              null comment '最后回复人姓名',
    created_time           datetime                                        default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time           datetime                                        default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint post_ibfk_1
        foreign key (course_id) references course (id)
            on delete cascade,
    constraint post_ibfk_2
        foreign key (user_id) references user (id)
            on delete cascade
)
    comment '帖子表';

create table comment
(
    id           bigint auto_increment comment '评论ID'
        primary key,
    post_id      bigint                                               not null comment '帖子ID',
    user_id      bigint                                               not null comment '评论用户ID',
    parent_id    bigint                                               null comment '父评论ID',
    content      text                                                 not null comment '评论内容',
    status       enum ('normal', 'deleted') default 'normal'          null comment '评论状态',
    like_count   int                        default 0                 null comment '点赞数',
    created_time datetime                   default CURRENT_TIMESTAMP null comment '创建时间',
    updated_time datetime                   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint comment_ibfk_1
        foreign key (post_id) references post (id)
            on delete cascade,
    constraint comment_ibfk_2
        foreign key (user_id) references user (id)
            on delete cascade,
    constraint comment_ibfk_3
        foreign key (parent_id) references comment (id)
            on delete cascade
)
    comment '评论表';

create index parent_id
    on comment (parent_id);

create index post_id
    on comment (post_id);

create index user_id
    on comment (user_id);

create index course_id
    on post (course_id);

create index user_id
    on post (user_id);

create index idx_user_username
    on user (username);

create table user_course
(
    id                    bigint auto_increment comment '关系ID'
        primary key,
    user_id               bigint                                                            not null comment '学生ID',
    course_id             bigint                                                            not null comment '课程ID',
    enrolled_time         datetime                                default CURRENT_TIMESTAMP null comment '报名时间',
    progress_rate         decimal(5, 2)                           default 0.00              null comment '学习进度百分比',
    completed_chapters    int                                     default 0                 null comment '已完成章节数',
    total_chapters        int                                     default 0                 null comment '总章节数',
    completed_materials   int                                     default 0                 null comment '已完成资料数',
    total_materials       int                                     default 0                 null comment '总资料数',
    progress_updated_time datetime                                default CURRENT_TIMESTAMP null comment '进度更新时间',
    last_accessed         datetime                                                          null comment '最后访问时间',
    completion_time       datetime                                                          null comment '完成时间',
    status                enum ('active', 'completed', 'dropped') default 'active'          null comment '学习状态',
    constraint uk_user_course
        unique (user_id, course_id),
    constraint user_course_ibfk_1
        foreign key (user_id) references user (id)
            on delete cascade,
    constraint user_course_ibfk_2
        foreign key (course_id) references course (id)
            on delete cascade
)
    comment '用户课程关系表';

create index course_id
    on user_course (course_id);

create table user_role
(
    id           bigint auto_increment comment '关系ID'
        primary key,
    user_id      bigint                             not null comment '用户ID',
    role_id      bigint                             not null comment '角色ID',
    created_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    constraint uk_user_role
        unique (user_id, role_id),
    constraint user_role_ibfk_1
        foreign key (user_id) references user (id)
            on delete cascade,
    constraint user_role_ibfk_2
        foreign key (role_id) references role (id)
            on delete cascade
)
    comment '用户角色关系表';

create index idx_user_role_composite
    on user_role (user_id, role_id);

create index idx_user_role_user
    on user_role (user_id);

create index role_id
    on user_role (role_id);


