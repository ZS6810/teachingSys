# 前端对接文档 (Frontend Integration Documentation)

> **文档状态**: Draft  
> **最后更新**: 2025-12-27  
> **后端版本**: 1.0.0

本文档根据后端 Java Spring Boot 代码生成，详细描述了前端与后端交互的接口规范。

---

## 1. 接口基础信息 (Basic Info)

*   **服务地址 (Base URL)**: `http://localhost:8080/api` (开发环境)
*   **请求协议**: `HTTP`
*   **认证方式**: **Session Cookie**
    *   登录成功后，后端会设置 `JSESSIONID` Cookie。
    *   后续所有请求（除 `/auth/*` 外）必须携带此 Cookie。
    *   前端需配置 Axios/Fetch 的 `withCredentials: true`。
*   **数据格式**:
    *   请求体: `application/json` (特殊接口如文件上传除外)
    *   响应体: `application/json`

---

## 2. 接口详细说明 (Interface Details)

### 2.1 认证模块 (Auth)

> **注意**: 认证模块统一使用 `ApiResponse` 包装结构返回。

#### 2.1.1 用户注册

*   **路径**: `/auth/register`
*   **方法**: `POST`
*   **描述**: 注册新用户。
*   **请求参数 (JSON)**:

| 参数名 | 类型 | 必填 | 说明 | 示例值 |
| :--- | :--- | :--- | :--- | :--- |
| `username` | String | 是 | 用户名 (唯一) | `"student1"` |
| `password` | String | 是 | 密码 | `"123456"` |
| `email` | String | 是 | 邮箱 (唯一) | `"test@example.com"` |
| `realName` | String | 是 | 真实姓名 | `"张三"` |
| `phone` | String | 否 | 手机号 | `"13800138000"` |
| `userType` | String | 是 | 用户类型 | `"student"`, `"teacher"`, `"admin"` |
| `qualificationProof` | String | 否 | 教师资质证明(教师必填) | `"url_to_image"` |

*   **响应结构 (`ApiResponse<AuthResponse>`)**:

```json
{
  "success": true,
  "message": "注册成功",
  "data": {
    "userId": 1,
    "username": "student1",
    "email": "test@example.com",
    "realName": "张三",
    "userType": "student",
    "status": "active",
    "roles": null
  }
}
```

#### 2.1.2 用户登录

*   **路径**: `/auth/login`
*   **方法**: `POST`
*   **请求参数 (JSON)**:

| 参数名 | 类型 | 必填 | 说明 |
| :--- | :--- | :--- | :--- |
| `username` | String | 是 | 用户名 |
| `password` | String | 是 | 密码 |

*   **响应结构**: 同注册接口。成功后浏览器会自动保存 `JSESSIONID`。

#### 2.1.3 获取当前用户

*   **路径**: `/auth/me`
*   **方法**: `GET`
*   **描述**: 根据 Session 获取当前登录用户信息。
*   **响应结构**: 同注册接口。

#### 2.1.4 退出登录

*   **路径**: `/auth/logout`
*   **方法**: `POST`
*   **响应结构**:

```json
{
  "success": true,
  "message": "登出成功",
  "data": null
}
```

---

### 2.2 课程模块 (Course)

> **注意**: 课程模块目前的实现**直接返回实体对象**或 `Page` 对象，**没有**外层包裹 `ApiResponse`。前端需直接解析数据。

#### 2.2.1 创建课程

*   **路径**: `/courses`
*   **方法**: `POST`
*   **请求参数 (JSON)**:

| 参数名 | 类型 | 必填 | 说明 |
| :--- | :--- | :--- | :--- |
| `courseName` | String | 是 | 课程名称 |
| `description` | String | 否 | 课程描述 |
| `price` | Number | 否 | 价格 |
| `teacher` | Object | 是 | 关联教师对象 |
| `teacher.id` | Number | 是 | 教师ID |

**请求示例**:
```json
{
  "courseName": "Java基础教程",
  "description": "入门首选",
  "price": 99.00,
  "teacher": {
    "id": 1
  }
}
```

*   **响应**: 返回创建完整的 Course 对象。

#### 2.2.2 获取课程详情

*   **路径**: `/courses/{id}`
*   **方法**: `GET`
*   **响应**: Course 对象 JSON。

#### 2.2.3 分页获取所有课程

*   **路径**: `/courses`
*   **方法**: `GET`
*   **参数**: `page` (页码, 0开始), `size` (每页条数, 默认20), `sort` (排序)
*   **响应 (Spring Page结构)**:

```json
{
  "content": [
    { "id": 1, "courseName": "Java基础教程", ... }
  ],
  "pageable": { ... },
  "totalElements": 100,
  "totalPages": 5,
  "last": false,
  "size": 20,
  "number": 0,
  ...
}
```

---

### 2.3 用户模块 (User)

> **注意**: 直接返回实体对象。

#### 2.3.1 获取用户信息

*   **路径**: `/users/{id}`
*   **方法**: `GET`
*   **响应**: User 对象 JSON。

---

### 2.4 作业与批改模块 (Assignment & Grading)

> **注意**: 此模块使用 `ApiResponse` 包装结构。

#### 2.4.1 获取作业提交详情 (用于批改页面)

*   **路径**: `/assignment-submissions/business/{submissionId}/detail`
*   **方法**: `GET`
*   **描述**: 获取单个提交的详细信息，包含题目内容、学生答案、参考答案。
*   **响应结构 (`ApiResponse<SubmissionDetailResponse>`)**:

```json
{
  "success": true,
  "message": "操作成功",
  "data": {
    "submissionId": 101,
    "assignmentId": 10,
    "assignmentTitle": "期中测试",
    "studentId": 5,
    "studentName": "李四",
    "totalScore": 0.00,
    "maxScore": 100.00,
    "feedback": null,
    "status": "submitted",
    "questions": [
      {
        "questionId": 21,
        "questionText": "Spring Boot 的核心注解是？",
        "questionType": "choice",
        "score": 5.00,
        "optionA": "@SpringBootApplication",
        "optionB": "@Controller",
        "optionC": "@Service",
        "optionD": "@Component",
        "studentAnswer": "A",
        "correctAnswer": "A",
        "explanation": "..."
      },
      {
        "questionId": 22,
        "questionText": "简述 IoC 容器的作用。",
        "questionType": "short_answer",
        "score": 10.00,
        "studentAnswer": "控制反转...",
        "correctAnswer": "IoC 容器负责实例化、配置和组装 Bean...",
        "explanation": "..."
      }
    ]
  }
}
```

#### 2.4.2 提交批改结果

*   **路径**: `/assignment-submissions/business/{submissionId}/grade`
*   **方法**: `POST`
*   **请求参数 (JSON)**:

| 参数名 | 类型 | 必填 | 说明 |
| :--- | :--- | :--- | :--- |
| `totalScore` | Number | 是 | 总得分 |
| `feedback` | String | 否 | 教师评语 |
| `autoGradedScore` | Number | 否 | 自动评分部分得分(可选) |

**请求示例**:
```json
{
  "totalScore": 85.5,
  "feedback": "回答得很好，但简答题需要更详细。",
  "autoGradedScore": 40.0
}
```

*   **响应**: 返回更新后的 `AssignmentSubmission` 对象 (包装在 ApiResponse 中)。

---

## 3. 错误码与统一说明 (Error Handling)

系统目前存在两种错误响应格式，前端需做兼容处理。

### 3.1 业务逻辑错误 (ApiResponse)

主要出现在认证 (`/auth/*`) 和部分业务逻辑接口。

```json
{
  "success": false,
  "message": "错误描述信息",
  "data": null
}
```

### 3.2 HTTP 标准错误 / 框架错误

主要出现在 CRUD 接口或系统异常。

*   **401 Unauthorized**: 未登录或 Session 过期。
*   **403 Forbidden**: 无权限。
*   **404 Not Found**: 资源不存在。
*   **500 Internal Server Error**: 服务器内部错误。

---

## 4. 接口调用示例 (Examples)

### 4.1 Axios 配置示例

```javascript
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  withCredentials: true, // 关键：允许携带 Cookie
  timeout: 5000
});

// 响应拦截器：统一处理错误
api.interceptors.response.use(
  response => {
    // 兼容 ApiResponse 结构
    if (response.data && response.data.hasOwnProperty('success')) {
      if (!response.data.success) {
        return Promise.reject(new Error(response.data.message));
      }
      return response.data.data; // 解包 data
    }
    return response.data; // 直接返回实体
  },
  error => {
    if (error.response && error.response.status === 401) {
      // 跳转登录
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;
```

### 4.2 Curl 登录示例

```bash
# 1. 登录并保存 Cookie
curl -v -c cookies.txt -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin", "password":"password"}'

# 2. 携带 Cookie 访问接口
curl -v -b cookies.txt http://localhost:8080/api/auth/me
```

---

## 5. 版本变更记录 (Version History)

| 版本 | 日期 | 修改人 | 说明 |
| :--- | :--- | :--- | :--- |
| v1.0.0 | 2025-12-27 | System | 初始版本生成，包含基础认证和核心业务CRUD。 |
