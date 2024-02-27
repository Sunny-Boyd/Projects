package com.example.finalproject;

import java.util.Random;

public class NPC {
    private int str;
    private int dex;
    private int intel;
    private int hp;
    private Random random;

    public NPC(Random random) {
        this.random = random;
        this.hp = random.nextInt(6) + 1;
        this.str = (random.nextInt(6) + 1) * 2;
        this.dex = (random.nextInt(6) + 1) * 2;
        this.intel = (random.nextInt(6) + 1) * 2;
    }

    public NPC(int hp, int str, int dex, int intel, Random random) {
    }

    public boolean alive() {
        return hp > 0;
    }

    public void dmgTaken( int dmg ) {
        hp -= dmg;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getIntel() {
        return intel;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public void attack(Player player) {
        int roll = random.nextInt(20) + 1;
        if (roll >= player.getDex()) {
            int damage = str / 3;
            player.takeDmg(damage);
        } else {
            System.out.println("NPC misses");
        }
    }

}
