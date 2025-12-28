# 课程推荐接口对接文档（猜你喜欢 / Neo4j 协同过滤）

> **文档状态**: Draft  
> **最后更新**: 2025-12-28  
> **后端版本**: 1.0.0

本文档用于前后端对接“猜你喜欢（课程推荐）”功能。该功能由 Neo4j 图数据库提供支撑，推荐策略为协同过滤：根据与当前学生选课重叠的其他学生的选课情况，推荐当前学生未选过的课程，并按出现频率排序。

---

## 1. 接口基础信息

*   **服务地址 (Base URL)**: `http://localhost:8080/api` (开发环境)
*   **请求协议**: `HTTP`
*   **认证方式**: **Session Cookie**
    *   登录成功后，后端会设置 `JSESSIONID` Cookie。
    *   本接口路径在 `/api/**` 下，默认需要携带 Cookie，否则返回 `401`。
    *   前端需配置 Axios/Fetch 的 `withCredentials: true`。
*   **数据格式**:
    *   请求体: 无（GET）
    *   响应体: `application/json`

---

## 2. 接口详细说明

### 2.1 获取课程推荐列表（Top 5）

*   **路径**: `/recommendations/{studentId}`
*   **方法**: `GET`
*   **描述**: 获取指定学生的“猜你喜欢（课程推荐）”列表（默认 Top 5）。
*   **路径参数 (Path Params)**:

| 参数名 | 类型 | 必填 | 说明 | 示例值 |
| :--- | :--- | :--- | :--- | :--- |
| `studentId` | Long | 是 | 学生 ID（对应 MySQL user.id） | `1` |

*   **响应结构 (`ApiResponse<List<GraphCourseRecommendationDto>>`)**:

字段说明：

| 字段名 | 类型 | 说明 |
| :--- | :--- | :--- |
| `success` | Boolean | 是否成功 |
| `message` | String | 提示信息 |
| `data` | Array | 推荐列表 |
| `data[].id` | Long | 课程 ID（对应 MySQL course.id） |
| `data[].name` | String | 课程名称（用于展示） |
| `data[].frequency` | Long | 频次：有多少“相似学生”选过该课 |

成功示例：

```json
{
  "success": true,
  "message": "操作成功",
  "data": [
    { "id": 12, "name": "数据结构", "frequency": 7 },
    { "id": 8, "name": "操作系统", "frequency": 5 },
    { "id": 20, "name": "计算机网络", "frequency": 4 }
  ]
}
```

常见错误：

| HTTP 状态码 | 场景 | 响应示例 |
| :--- | :--- | :--- |
| `401` | 未登录或 Session 过期 | `{"success":false,"message":"未登录或Session已过期"}` |
| `500` | Neo4j 连接异常或服务端异常 | 由后端统一异常处理返回（若项目未统一处理，可能为默认错误页/JSON） |

---

## 3. 前端调用示例

### 3.1 Axios 示例

```js
import axios from "axios";

export async function getRecommendations(studentId) {
  const res = await axios.get(
    `http://localhost:8080/api/recommendations/${studentId}`,
    { withCredentials: true }
  );
  return res.data;
}
```

### 3.2 Fetch 示例

```js
export async function getRecommendations(studentId) {
  const res = await fetch(`http://localhost:8080/api/recommendations/${studentId}`, {
    method: "GET",
    credentials: "include"
  });
  return await res.json();
}
```

