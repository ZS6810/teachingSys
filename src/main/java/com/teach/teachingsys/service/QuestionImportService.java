package com.teach.teachingsys.service;

import com.teach.teachingsys.dto.ImportQuestionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuestionImportService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public ImportQuestionsResponse importQuestions(Long courseId, Long assignmentId, String questionsJson) {
        if (questionsJson == null || questionsJson.isBlank()) {
            throw new RuntimeException("题目数据不能为空");
        }

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "CALL sp_import_questions(?, ?, ?)",
                courseId,
                assignmentId,
                questionsJson
        );

        if (rows.isEmpty()) {
            throw new RuntimeException("导入失败：存储过程未返回结果");
        }

        Map<String, Object> row = rows.get(0);

        return ImportQuestionsResponse.builder()
                .importedCount(toInt(row.get("imported_count")))
                .errorCount(toInt(row.get("error_count")))
                .message(Objects.toString(row.get("message"), ""))
                .errorDetails(Objects.toString(row.get("error_details"), ""))
                .build();
    }

    private Integer toInt(Object value) {
        if (value == null) {
            return 0;
        }
        if (value instanceof Number number) {
            return number.intValue();
        }
        return Integer.parseInt(value.toString());
    }
}

