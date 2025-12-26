package com.teach.teachingsys.entity.enums;

public final class ProgressEnums {

    private ProgressEnums() {
    }

    public enum ProgressType {
        chapter,
        material
    }

    public enum LearningStatus {
        not_started,
        in_progress,
        completed
    }
}

