package com.example.book.entity.enums;

public enum RoleEnum {
    ADMIN(0), USER(1),SUPER_ADMIN(2);
    private Integer value;
    RoleEnum(Integer value) {
        this.value = value;
    }

}
