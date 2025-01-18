package com.example.gdgoc_2025_whitesheepserver.global.enums;

public enum LevelType {
    easy(1),
    medium(2),
    hard(3);

    private int level;

    LevelType(int level) {
        this.level = level;
    }

    public static LevelType getByLevel(int level) {
        for (LevelType levelType : LevelType.values()) {
            if (levelType.level == level) {
                return levelType;
            }
        }
        throw new IllegalArgumentException("not found type");
    }
}
