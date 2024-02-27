package com.example.finalproject;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Random;

public class Player {
    private int hp;
    private int str;
    private int dex;
    private int intelligence;
    private int totalGold;
    private Random random;

    private Label hitPointsLabel;
    private Label strengthLabel;
    private Label dexterityLabel;
    private Label intelligenceLabel;
    private Label goldLabel;

    public Player(int hp, int str, int dex, int intelligence, int totalGold) {
        this.hp = hp;
        this.str = str;
        this.dex = dex;
        this.intelligence = intelligence;
        this.totalGold = totalGold;
    }

    public Player() {
        random = new Random();
        resetPlayer();
    }

    private void resetPlayer() {
        hp = 20;
        str = rollDice(3, 6);
        dex = rollDice(3, 6);
        intelligence = rollDice(3, 6);
        totalGold = 0;
    }

    public boolean alive() {
        return hp > 0;
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

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public void addGold(int amount) {
        totalGold += amount;
        updateCharacterStats();
    }

    public int rollDice(int rolls, int sides) {
        int result = 0;
        for (int i = 0; i < rolls; i++) {
            result += random.nextInt(sides) + 1;
        }
        return result;
    }

    public boolean canSearchRoom() {
        int roll = random.nextInt(20) + 1;
        return roll <= intelligence;
    }

    public boolean canFight(NPC npc) {
        int roll = random.nextInt(20) + 1;
        return roll >= npc.getDex();
    }

    public void attack(NPC npc) {
        int dmg = str / 3;
        npc.dmgTaken(dmg);
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > 20) {
            hp = 20;
        }
    }

    public void sleep() {
        int sleepResult = random.nextInt(6) + 1;
        if (sleepResult == 1) {
            handleNPCEncounter();
        } else {
            hp = 20;
            updateCharacterStats();
        }
    }

    private void handleNPCEncounter() {
        NPC npc = createRandomNPC();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NPC Encounter");
        alert.setHeaderText(null);
        alert.setContentText("While sleeping, you were ambushed by an NPC!");

        ButtonType fightButton = new ButtonType("Fight");
        ButtonType runButton = new ButtonType("Run Away");

        alert.getButtonTypes().setAll(fightButton, runButton);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == fightButton) {
                handleFight(npc);
            } else if (buttonType == runButton) {
                handleRunAway(npc);
            }
        });
    }

    private void handleFight(NPC npc) {
        if (canFight(npc)) {
            attack(npc);
            if (!npc.alive()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Victory");
                alert.setHeaderText(null);
                alert.setContentText("You defeated the NPC!");

                alert.showAndWait();
            } else {
                npc.attack(this);
                if (!alive()) {
                    gameOver();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missed Attack");
            alert.setHeaderText(null);
            alert.setContentText("You missed the attack!");

            alert.showAndWait();

            npc.attack(this);
            if (!alive()) {
                gameOver();
            }
        }

        updateCharacterStats();
    }

    private void handleRunAway(NPC npc) {
        if (canRunAway(npc)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful Escape");
            alert.setHeaderText(null);
            alert.setContentText("You successfully ran away from the NPC!");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Caught During Escape");
            alert.setHeaderText(null);
            alert.setContentText("The NPC caught you while trying to run away!");

            alert.showAndWait();

            npc.attack(this);
            if (!alive()) {
                gameOver();
            }
        }

        updateCharacterStats();
    }

    void updateCharacterStats() {
        hitPointsLabel.setText("Hit Points: " + getHp());
        strengthLabel.setText("Strength: " + getStr());
        dexterityLabel.setText("Dexterity: " + getDex());
        intelligenceLabel.setText("Intelligence: " + getIntelligence());
        goldLabel.setText("Gold: " + getTotalGold());
    }

    private NPC createRandomNPC() {
        int hp = random.nextInt(6) + 1;
        int str = random.nextInt(6) * 2;
        int dex = random.nextInt(6) * 2;
        int intel = random.nextInt(6) * 2;

        return new NPC(hp, str, dex, intel, random);
    }

    public void takeDmg(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            gameOver();
        }
    }

    public boolean canRunAway(NPC npc) {
        int roll = random.nextInt(20) + 1;
        return roll < npc.getIntel();
    }

    private void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over!");
        alert.setHeaderText(null);
        alert.setContentText("You have died, game over!");

        ButtonType restartButton = new ButtonType("Restart");
        ButtonType quitButton = new ButtonType("Quit");

        alert.getButtonTypes().setAll(restartButton, quitButton);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == restartButton) {
                restartGame();
            } else if (buttonType == quitButton) {
                System.exit(0);
            }
        });
    }

    private void restartGame() {
        resetPlayer();
    }

    public void setHitPointsLabel(Label hitPointsLabel) {
        this.hitPointsLabel = hitPointsLabel;
    }

    public void setStrengthLabel(Label strengthLabel) {
        this.strengthLabel = strengthLabel;
    }

    public void setDexterityLabel(Label dexterityLabel) {
        this.dexterityLabel = dexterityLabel;
    }

    public void setIntelligenceLabel(Label intelligenceLabel) {
        this.intelligenceLabel = intelligenceLabel;
    }

    public void setGoldLabel(Label goldLabel) {
        this.goldLabel = goldLabel;
    }
}
