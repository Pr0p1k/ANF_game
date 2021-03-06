package com.p3212.EntityClasses;

/**
 * Represents an attack with damage and consumed chakra. Made to easily send response
 */
public class Attack {
    private int damage;
    private int chakra;
    private boolean deadly = false;
    private int code;

    public Attack() {
        damage = 0;
        chakra = 0;
        code = 0;
    }

    public Attack(int damage, int chakra) {
        this.damage = damage;
        this.chakra = chakra;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getChakra() {
        return chakra;
    }

    public void setChakra(int chakra) {
        this.chakra = chakra;
    }

    public boolean isDeadly() {
        return deadly;
    }

    public void setDeadly(boolean deadly) {
        this.deadly = deadly;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"damage\": " + damage +
                ",\n\"chakra\": " + chakra +
                ",\n\"deadly\": " + deadly +
                ",\n\"code\": " + code +
                "\n}";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
