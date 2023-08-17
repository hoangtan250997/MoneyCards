package com.hoangtan.moneycards.entity;

public enum JarType {
    NECESSITIES(1),
    FINANCIAL_FREEDOM(2),
    LONG_TERM_SAVINGS(3),
    EDUCATION(4),
    PLAY(5),
    GIVE(6),
    FREE_MONEY(7);

    private int value;

    JarType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }



}
