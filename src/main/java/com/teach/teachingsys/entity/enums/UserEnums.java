package com.teach.teachingsys.entity.enums;

public final class UserEnums {

    private UserEnums() {
    }

    public enum UserType {
        student,
        teacher,
        admin
    }

    public enum UserStatus {
        active,
        inactive,
        pending
    }
}

