package com.teach.teachingsys.entity.enums;

/**
 * 题目相关枚举。
 * 与数据库 enum 值保持一致，使用小写存储。
 */
public final class QuestionEnums {

    private QuestionEnums() {
    }

    public enum QuestionType {
        choice,
        short_answer
    }

    public enum Difficulty {
        easy,
        medium,
        hard
    }
}

