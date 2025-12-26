package com.teach.teachingsys.entity.enums;

public final class CourseEnums {

    private CourseEnums() {
    }

    public enum CourseStatus {
        draft,
        pending,
        approved,
        rejected,
        published
    }

    public enum CourseLevel {
        beginner,
        intermediate,
        advanced
    }
}

