package com.dzj.enums;

/**
 * @author Devin13 on 2018/9/4.
 * @version 1.0
 */
public enum  VideoStatusEnum {
    SUCCESS(1),
    FORBID(2);

    public final int value;

    VideoStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
