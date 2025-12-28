为了方便后端开发人员及 LLM（大语言模型）理解并调用该存储过程，我为你整理了一份标准化的技术接口文档。这份文档详细说明了接口的功能、输入参数格式、业务逻辑及返回结果。

---

# 存储过程接口文档：sp_import_questions

## 1. 功能概述

该存储过程用于**批量导入题目数据**。支持同时导入选择题（单选/多选）和简答题，并可选地将题目自动关联到指定的课程（Course）或作业（Assignment）。

## 2. 输入参数定义

| 参数名 | 数据类型 | 必须 | 说明 |
| --- | --- | --- | --- |
| `p_course_id` | BIGINT | 否 | 课程ID。若提供且未指定作业ID，题目将打上该课程标签。 |
| `p_assignment_id` | BIGINT | 否 | 作业ID。若提供，题目将自动加入该作业并更新作业总分。 |
| `p_questions_json` | JSON | 是 | 题目数据数组。格式见下文。 |

## 3. 题目 JSON 数据格式规格

`p_questions_json` 应为一个包含多个对象的 JSON 数组。

### 3.1 通用必需字段 (所有题型)

* `question_text` (String): 题目内容。
* `question_type` (String): 枚举值 `choice` (选择题) 或 `short_answer` (简答题)。

### 3.2 通用可选字段

* `difficulty` (String): 难度级别：`easy`, `medium` (默认), `hard`。
* `score` (Decimal/String): 题目分值，默认为 `1.00`。
* `explanation` (String): 答案解析。
* `tags` (JSON Object): 题目自定义标签（如 `{"knowledge_point": "Spring"}`）。

### 3.3 选择题特有字段 (`question_type` = 'choice')

* `option_a` 到 `option_f` (String): 选项内容。
* `correct_answer` (String, **必填**): 正确答案字母（如 `A` 或多选 `ABC`）。
* `is_multiple` (Number): 是否为多选题：`1` (是), `0` (否)。

### 3.4 简答题特有字段 (`question_type` = 'short_answer')

* `reference_answer` (String): 参考答案。
* `answer_length_limit` (Number): 字数限制。

---

## 4. 业务逻辑与约束

1. **事务完整性**：整个导入过程包裹在事务中。若发生严重系统异常，所有操作回滚。
2. **容错机制**：
* 过程内部循环处理每一道题。单道题目格式错误（如选择题没给答案）会跳过并记录在 `error_details` 中，不会中断其他题目的导入。


3. **关联逻辑**：
* 若关联到 `p_assignment_id`：自动在 `assignment_bank` 创建关系，并按导入顺序生成 `question_order`。
* **自动算分**：成功导入后，存储过程会自动重新计算并更新 `assignment` 表的 `total_score`。


4. **验证**：会检查 `p_assignment_id` 是否属于指定的 `p_course_id`。

---

## 5. 输出结果 (Result Set)

执行成功后返回一行数据，包含以下字段：

| 字段名 | 说明 |
| --- | --- |
| `imported_count` | 成功导入的题目总数。 |
| `error_count` | 导入失败的题目总数。 |
| `message` | 执行状态简报。 |
| `error_details` | 详细的错误说明（包含具体是哪一题、哪一项报错）。 |

---

## 6. 后端调用示例 (SQL)

```sql
CALL sp_import_questions(
    1, -- 课程ID
    10, -- 作业ID
    '[
        {
            "question_type": "choice",
            "question_text": "MySQL默认端口号是多少？",
            "difficulty": "easy",
            "score": 5.0,
            "option_a": "3306",
            "option_b": "8080",
            "correct_answer": "A",
            "is_multiple": 0
        },
        {
            "question_type": "short_answer",
            "question_text": "请简述什么是范式。",
            "score": 10.0,
            "reference_answer": "范式是关系数据库设计的基本准则...",
            "answer_length_limit": 500
        }
    ]'
);

```

---

## 7. 前端对接文档（HTTP API）

### 7.1 接口信息

* **URL**：`POST /api/questions/import`
* **Content-Type**：`application/json`
* **认证方式**：基于 Session 的 Cookie 登录态（前端请求需要携带 Cookie）

### 7.2 请求参数（Body）

Body 为 JSON 对象：

| 字段名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| `courseId` | Number | 否 | 课程ID。对应存储过程 `p_course_id`。 |
| `assignmentId` | Number | 否 | 作业ID。对应存储过程 `p_assignment_id`。 |
| `questions` | Array | 是 | 题目数组。每个元素结构见 **第 3 节**。 |

请求示例：

```json
{
  "courseId": 1,
  "assignmentId": 10,
  "questions": [
    {
      "question_type": "choice",
      "question_text": "MySQL默认端口号是多少？",
      "difficulty": "easy",
      "score": 5.0,
      "option_a": "3306",
      "option_b": "8080",
      "correct_answer": "A",
      "is_multiple": 0
    },
    {
      "question_type": "short_answer",
      "question_text": "请简述什么是范式。",
      "score": 10.0,
      "reference_answer": "范式是关系数据库设计的基本准则...",
      "answer_length_limit": 500
    }
  ]
}
```

### 7.3 返回结果

接口统一返回结构为：

| 字段名 | 类型 | 说明 |
| --- | --- | --- |
| `success` | Boolean | 是否成功 |
| `message` | String | 状态信息（一般为存储过程返回的 `message`） |
| `data` | Object | 结果数据，见下表 |

`data` 字段结构：

| 字段名 | 类型 | 说明 |
| --- | --- | --- |
| `importedCount` | Number | 成功导入数量 |
| `errorCount` | Number | 失败数量 |
| `message` | String | 存储过程返回的执行摘要 |
| `errorDetails` | String | 存储过程返回的错误明细（可能是 JSON 字符串） |

成功示例：

```json
{
  "success": true,
  "message": "导入完成",
  "data": {
    "importedCount": 2,
    "errorCount": 0,
    "message": "导入完成",
    "errorDetails": ""
  }
}
```

失败示例（如未登录、或 questions 不是数组等）：

```json
{
  "success": false,
  "message": "请先登录",
  "data": null
}
```

### 7.4 前端调用注意事项

* **必须携带 Cookie**：例如 `axios` 需设置 `withCredentials: true`。
* `questions` 必须是 JSON 数组；元素字段规范见 **第 3 节**。
* 存储过程具备容错：单题失败不会影响其他题导入，细节在 `errorDetails` 中查看。
