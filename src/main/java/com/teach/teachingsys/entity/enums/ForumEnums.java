package com.teach.teachingsys.entity.enums;

public final class ForumEnums {

    private ForumEnums() {
    }

    public enum PostType {
        discussion,
        question,
        announcement
    }

    public enum PostStatus {
        normal,
        pinned,
        deleted
    }

    public enum CommentStatus {
        normal,
        deleted
    }
}

