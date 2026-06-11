create
definer = root@localhost procedure sp_generate_student_report(IN p_user_id bigint, IN p_course_id bigint)
BEGIN
    -- 1. 学生基本信息
SELECT
    u.id,
    u.username,
    u.real_name,
    u.email,
    u.user_type,
    u.status,
    u.last_login_time,
    COUNT(DISTINCT uc.course_id) AS enrolled_courses_count,
    COUNT(DISTINCT CASE WHEN uc.status = 'completed' THEN uc.course_id END) AS completed_courses_count
FROM user u
         LEFT JOIN user_course uc ON u.id = uc.user_id
WHERE u.id = p_user_id
GROUP BY u.id;

-- 2. 课程学习进度（如果指定课程ID，则只返回该课程）
IF p_course_id IS NULL THEN
        -- 所有课程的学习进度
SELECT
    c.id AS course_id,
    c.course_name,
    c.teacher_name,
    c.level,
    uc.progress_rate,
    uc.completed_chapters,
    uc.total_chapters,
    uc.completed_materials,
    uc.total_materials,
    uc.enrolled_time,
    uc.last_accessed,
    uc.status AS learning_status,
    CASE
        WHEN uc.progress_rate >= 100 THEN '已完成'
        WHEN uc.progress_rate >= 70 THEN '进行中（良好）'
        WHEN uc.progress_rate >= 30 THEN '进行中（一般）'
        ELSE '刚开始'
        END AS progress_level
FROM user_course uc
         JOIN course c ON uc.course_id = c.id
WHERE uc.user_id = p_user_id
ORDER BY uc.last_accessed DESC;
ELSE
        -- 特定课程的详细学习进度
SELECT
    c.id AS course_id,
    c.course_name,
    c.description,
    c.teacher_name,
    c.level,
    uc.progress_rate,
    uc.completed_chapters,
    uc.total_chapters,
    uc.completed_materials,
    uc.total_materials,
    uc.enrolled_time,
    uc.last_accessed,
    uc.completion_time,
    uc.status AS learning_status
FROM user_course uc
         JOIN course c ON uc.course_id = c.id
WHERE uc.user_id = p_user_id
  AND uc.course_id = p_course_id;

-- 章节级别的学习进度
SELECT
    ch.id AS chapter_id,
    ch.chapter_name,
    ch.chapter_order,
    lp.status AS learning_status,
    lp.completion_percentage,
    lp.last_studied_time,
    lp.completed_time,
    COUNT(DISTINCT cm.id) AS total_materials,
    COUNT(DISTINCT CASE WHEN lp2.status = 'completed' THEN lp2.material_id END) AS completed_materials
FROM chapter ch
         LEFT JOIN learningprogress lp ON ch.id = lp.chapter_id AND lp.user_id = p_user_id
         LEFT JOIN coursematerial cm ON ch.id = cm.chapter_id
         LEFT JOIN learningprogress lp2 ON cm.id = lp2.material_id AND lp2.user_id = p_user_id
WHERE ch.course_id = p_course_id
GROUP BY ch.id, ch.chapter_name, ch.chapter_order, lp.status, lp.completion_percentage,
         lp.last_studied_time, lp.completed_time
ORDER BY ch.chapter_order;
END IF;

    -- 3. 作业完成情况
SELECT
    a.id AS assignment_id,
    a.title,
    a.assignment_type,
    a.deadline,
    a.total_score,
    a.passing_score,
    asub.attempt_number,
    asub.submitted_time,
    asub.total_score AS obtained_score,
    asub.auto_graded_score,
    asub.status AS submission_status,
    asub.graded_time,
    CASE
        WHEN asub.total_score >= a.passing_score THEN '及格'
        WHEN asub.total_score > 0 THEN '不及格'
        ELSE '未提交'
        END AS result_status,
    ROUND((asub.total_score / a.total_score * 100), 2) AS score_percentage
FROM assignment a
         LEFT JOIN assignment_submission asub ON a.id = asub.assignment_id AND asub.user_id = p_user_id
WHERE a.course_id = COALESCE(p_course_id, a.course_id)
  AND EXISTS (
    SELECT 1 FROM user_course uc
    WHERE uc.user_id = p_user_id
      AND uc.course_id = a.course_id
)
ORDER BY a.deadline DESC, asub.submitted_time DESC;

-- 4. 学习时间统计（按周）
SELECT
    YEARWEEK(lp.last_studied_time) AS week_number,
    DATE_FORMAT(MIN(DATE(lp.last_studied_time)), '%Y-%m-%d') AS week_start,
    DATE_FORMAT(MAX(DATE(lp.last_studied_time)), '%Y-%m-%d') AS week_end,
    COUNT(DISTINCT DATE(lp.last_studied_time)) AS study_days,
    COUNT(DISTINCT lp.id) AS study_sessions,
    SUM(CASE WHEN lp.material_type = 'video' THEN lp.video_watch_time ELSE 0 END) AS video_watch_seconds,
    SUM(CASE WHEN lp.material_type != 'video' THEN 1 ELSE 0 END) AS material_studied_count
FROM learningprogress lp
WHERE lp.user_id = p_user_id
  AND lp.last_studied_time >= DATE_SUB(NOW(), INTERVAL 12 WEEK)
  AND COALESCE(p_course_id, lp.course_id) = lp.course_id
GROUP BY YEARWEEK(lp.last_studied_time)
ORDER BY week_number DESC;

END;

create
definer = root@localhost procedure sp_import_questions(IN p_course_id bigint, IN p_assignment_id bigint,
                                                           IN p_questions_json json)
BEGIN
    DECLARE v_current_index INT DEFAULT 0;
    DECLARE v_total_questions INT DEFAULT 0;
    DECLARE v_question_id BIGINT;
    DECLARE v_question_type ENUM('choice', 'short_answer');
    DECLARE v_question_text TEXT;
    DECLARE v_difficulty ENUM('easy', 'medium', 'hard');
    DECLARE v_score DECIMAL(5,2);
    DECLARE v_explanation TEXT;
    DECLARE v_tags JSON;
    DECLARE v_option_a, v_option_b, v_option_c, v_option_d, v_option_e, v_option_f TEXT;
    DECLARE v_correct_answer VARCHAR(10);
    DECLARE v_is_multiple TINYINT(1);
    DECLARE v_reference_answer TEXT;
    DECLARE v_answer_length_limit INT;
    DECLARE v_question_order INT DEFAULT 0;
    DECLARE v_success_count INT DEFAULT 0;
    DECLARE v_error_count INT DEFAULT 0;
    DECLARE v_error_messages TEXT DEFAULT '';
    DECLARE v_sql_state CHAR(5) DEFAULT '00000';
    DECLARE v_sql_error_msg TEXT;

    -- 异常处理
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
GET DIAGNOSTICS CONDITION 1
    v_sql_state = RETURNED_SQLSTATE,
    v_sql_error_msg = MESSAGE_TEXT;

-- 回滚事务
ROLLBACK;

-- 返回错误信息
SELECT
    0 AS imported_count,
    v_total_questions AS error_count,
    CONCAT('导入过程中发生错误: ', v_sql_error_msg) AS message,
    '' AS error_details;
END;

    -- 验证JSON格式
    IF p_questions_json IS NULL OR JSON_TYPE(p_questions_json) != 'ARRAY' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = '无效的题目数据格式，期望JSON数组';
END IF;

    -- 获取题目总数
    SET v_total_questions = JSON_LENGTH(p_questions_json);

    -- 如果没有题目，直接返回
    IF v_total_questions = 0 THEN
SELECT 0 AS imported_count, 0 AS error_count, '没有可导入的题目' AS message, '' AS error_details;
END IF;

    -- 验证作业和课程的关联关系（如果提供了作业ID）
    IF p_assignment_id IS NOT NULL THEN
        IF NOT EXISTS (
            SELECT 1 FROM assignment WHERE id = p_assignment_id
                                       AND (p_course_id IS NULL OR course_id = p_course_id)
        ) THEN
            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = '作业不存在或与课程不匹配';
END IF;
END IF;

    -- 开始事务
START TRANSACTION;

-- 循环处理每个题目
WHILE v_current_index < v_total_questions DO
BEGIN
                DECLARE v_current_question_text TEXT;
                DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
GET DIAGNOSTICS CONDITION 1
    v_sql_state = RETURNED_SQLSTATE,
    v_sql_error_msg = MESSAGE_TEXT;

SET v_error_count = v_error_count + 1;

                        -- 提取当前题目的文本用于错误信息
                        SET v_current_question_text = COALESCE(
                                JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].question_text'))),
                                CONCAT('第', v_current_index + 1, '题')
                                                      );

                        SET v_error_messages = CONCAT_WS('; ',
                                                         v_error_messages,
                                                         CONCAT('第', v_current_index + 1, '题: ',
                                                                v_current_question_text,
                                                                ' - 错误: ', v_sql_error_msg)
                                               );
END;

                -- 提取题目基本信息
                SET v_question_text = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].question_text')));
                SET v_question_type = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].question_type')));
                SET v_difficulty = COALESCE(JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].difficulty'))), 'medium');

                -- 处理分数（JSON_EXTRACT返回可能是字符串或数字）
                SET v_score = CAST(
                        COALESCE(
                                JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].score'))),
                                '1.00'
                        ) AS DECIMAL(5,2)
                              );

                SET v_explanation = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].explanation')));
                SET v_tags = JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].tags'));

                -- 验证必需字段
                IF v_question_text IS NULL OR v_question_type IS NULL THEN
                    SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT = '题目内容和类型为必填项';
END IF;

                -- 验证题目类型
                IF v_question_type NOT IN ('choice', 'short_answer') THEN
                    SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT = '题目类型必须是choice或short_answer';
END IF;

                -- 插入到题库主表
INSERT INTO questionbank (
    question_type,
    question_text,
    difficulty,
    score,
    explanation,
    tags,
    created_time,
    updated_time
) VALUES (
             v_question_type,
             v_question_text,
             v_difficulty,
             v_score,
             v_explanation,
             v_tags,
             NOW(),
             NOW()
         );

-- 获取刚插入的题目ID
SET v_question_id = LAST_INSERT_ID();

                -- 根据题目类型插入到相应的子表
                IF v_question_type = 'choice' THEN
                    -- 提取选择题特有字段
                    SET v_option_a = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].option_a')));
                    SET v_option_b = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].option_b')));
                    SET v_option_c = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].option_c')));
                    SET v_option_d = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].option_d')));
                    SET v_option_e = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].option_e')));
                    SET v_option_f = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].option_f')));
                    SET v_correct_answer = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].correct_answer')));
                    SET v_is_multiple = COALESCE(
                            CAST(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].is_multiple')) AS UNSIGNED),
                            0
                                        );

                    -- 验证选择题必需字段
                    IF v_correct_answer IS NULL THEN
                        -- 删除已插入的主表记录
DELETE FROM questionbank WHERE id = v_question_id;
SIGNAL SQLSTATE '45000'
                            SET MESSAGE_TEXT = '选择题必须提供正确答案';
END IF;

                    -- 插入选择题表
INSERT INTO choice_question (
    question_id,
    option_a,
    option_b,
    option_c,
    option_d,
    option_e,
    option_f,
    correct_answer,
    is_multiple
) VALUES (
             v_question_id,
             v_option_a,
             v_option_b,
             v_option_c,
             v_option_d,
             v_option_e,
             v_option_f,
             v_correct_answer,
             v_is_multiple
         );

ELSEIF v_question_type = 'short_answer' THEN
                    -- 提取简答题特有字段
                    SET v_reference_answer = JSON_UNQUOTE(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].reference_answer')));
                    SET v_answer_length_limit = COALESCE(
                            CAST(JSON_EXTRACT(p_questions_json, CONCAT('$[', v_current_index, '].answer_length_limit')) AS UNSIGNED),
                            NULL
                                                );

                    -- 插入简答题表
INSERT INTO short_answer_question (
    question_id,
    reference_answer,
    answer_length_limit
) VALUES (
             v_question_id,
             v_reference_answer,
             v_answer_length_limit
         );
END IF;

                -- 如果提供了作业ID，关联题目到作业
                IF p_assignment_id IS NOT NULL THEN
                    SET v_question_order = v_question_order + 1;

INSERT INTO assignment_bank (
    assignment_id,
    question_id,
    question_order
) VALUES (
             p_assignment_id,
             v_question_id,
             v_question_order
         ) ON DUPLICATE KEY UPDATE question_order = v_question_order;
END IF;

                -- 如果提供了课程ID（且未提供作业ID），关联题目到课程（通过课程标签）
                IF p_course_id IS NOT NULL AND p_assignment_id IS NULL THEN
                    -- 这里可以扩展为将题目添加到课程特定的题库中
                    -- 当前实现仅更新标签信息
                    IF v_tags IS NOT NULL THEN
UPDATE questionbank
SET tags = JSON_SET(
        COALESCE(tags, '{}'),
        '$.course_id', CAST(p_course_id AS CHAR)
           )
WHERE id = v_question_id;
END IF;
END IF;

                SET v_success_count = v_success_count + 1;
                SET v_current_index = v_current_index + 1;
END;
END WHILE;

    -- 如果关联到作业，更新作业统计信息
    IF p_assignment_id IS NOT NULL AND v_success_count > 0 THEN
        -- 更新作业的总分（假设每个题目分值已设置）
UPDATE assignment a
SET total_score = (
    SELECT COALESCE(SUM(qb.score), 0)
    FROM assignment_bank ab
             JOIN questionbank qb ON ab.question_id = qb.id
    WHERE ab.assignment_id = p_assignment_id
),
    updated_time = NOW()
WHERE a.id = p_assignment_id;
END IF;

    -- 提交事务
COMMIT;

-- 返回导入结果
SELECT
    v_success_count AS imported_count,
    v_error_count AS error_count,
    CASE
        WHEN v_error_count = 0 THEN CONCAT('成功导入 ', v_success_count, ' 道题目')
        ELSE CONCAT('成功导入 ', v_success_count, ' 题，失败 ', v_error_count, ' 题')
        END AS message,
    v_error_messages AS error_details;

END;

