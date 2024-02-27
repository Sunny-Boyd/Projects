package com.example.finalproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private int leftRight;
    private int upDown;
    private boolean blocked;
    private NPC npc;
    private int gold;

    public Room(int leftRight, int upDown, boolean blocked, NPC npc, int gold) {
        this.leftRight = leftRight;
        this.upDown = upDown;
        this.blocked = blocked;
        this.npc = npc;
        this.gold = gold;
    }

    public boolean blocked() {
        return blocked;
    }

    public boolean hasNPC() {
        return npc != null;
    }

    public int getLeftRight() {
        return leftRight;
    }

    public void setLeftRight(int leftRight) {
        this.leftRight = leftRight;
    }

    public int getUpDown() {
        return upDown;
    }

    public void setUpDown(int upDown) {
        this.upDown = upDown;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public void removeNPC() {
        this.npc = null;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public static List<List<Room>> createGameWorld(int rows, int columns) {
        Random random = new Random();
        List<List<Room>> world = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Room> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                boolean blocked = random.nextBoolean();
                NPC npc = blocked ? null : new NPC(random);
                int gold = random.nextInt(11) + 5;

                Room room = new Room(i, j, blocked, npc, gold);
                row.add(room);
            }
            world.add(row);
        }

        return world;
    }

    public static void main(String[] args) {
        int rows = 10;
        int columns = 10;

        List<List<Room>> gameWorld = createGameWorld(rows, columns);

        Room someRoom = gameWorld.get(2).get(3);
        System.out.println("Room at (2, 3): Blocked = " + someRoom.isBlocked() + ", Gold = " + someRoom.getGold());
    }
}
