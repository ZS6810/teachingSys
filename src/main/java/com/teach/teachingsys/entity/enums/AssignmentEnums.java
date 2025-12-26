package com.teach.teachingsys.entity.enums;

public final class AssignmentEnums {

    private AssignmentEnums() {
    }

    public enum AssignmentType {
        homework,
        exam
    }

    public enum AssignmentStatus {
        draft,
        published,
        closed
    }
}

