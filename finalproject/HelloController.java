package com.example.finalproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HelloController {

    private Player player;
    private Room[][] gameWorld;
    private int currentX;
    private int currentY;
    private boolean playerEngaged = false;
    private String lastMoveDirection = "";
    private boolean successfullyEscaped = false;
    private final Object lock = new Object();
    private boolean waitForUserInput = false;
    private boolean npcEncounterHandled = false;
    private boolean showOptionsDialog = false;


    @FXML
    private TextArea roomDescription;
    @FXML
    private Button moveButton;
    @FXML
    private Button fightButton;
    @FXML
    private Button runAwayButton;
    @FXML
    private Label hitPointsLabel;
    @FXML
    private Label strengthLabel;
    @FXML
    private Label dexterityLabel;
    @FXML
    private Label intelligenceLabel;
    @FXML
    private Label goldLabel;

    private Random random;


    @FXML
    public void initialize() {
        player = new Player();
        player.setHitPointsLabel(hitPointsLabel);
        player.setStrengthLabel(strengthLabel);
        player.setDexterityLabel(dexterityLabel);
        player.setIntelligenceLabel(intelligenceLabel);
        player.setGoldLabel(goldLabel);

        random = new Random();

        gameWorld = createGameWorld();

        Room initialRoom = gameWorld[currentX][currentY];
        if (initialRoom.hasNPC()) {
            handleNPCEncounter();
        }
        roomDescription.setText("");
        updateRoomDescription();

        Thread gameLoopThread = new Thread(this::gameLoop);
        gameLoopThread.setDaemon(true);
        gameLoopThread.start();

        player.updateCharacterStats();

        synchronized (lock) {
            waitForUserInput = true;
            lock.notify();
        }
    }

    private void gameLoop() {
        while (player.alive()) {
            synchronized (lock) {
                while (!waitForUserInput) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            if (player.alive()) {
                if (playerEngaged) {
                    playerEngaged = false;
                    successfullyEscaped = false;

                    synchronized (lock) {
                        waitForUserInput = false;
                        lock.notify();
                    }

                    if (npcEncounterHandled && showOptionsDialog) {
                        Platform.runLater(this::showOptionsDialog);
                        npcEncounterHandled = false;
                        showOptionsDialog = false;
                    }
                } else {
                    handleNPCEncounter();

                    movePlayerButton();

                    Platform.runLater(() -> {
                        updateRoomDescription();
                        player.updateCharacterStats();
                    });

                    synchronized (lock) {
                        waitForUserInput = false;
                        lock.notify();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        gameOver();
    }

    private Room[][] createGameWorld() {
        int rows = 10;
        int columns = 10;
        Room[][] world = new Room[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean blocked = i == 0 || i == rows - 1 || j == 0 || j == columns - 1;
                world[i][j] = new Room(i, j, blocked, null, 0);

                if (!blocked) {
                    int npcX = i;
                    int npcY = j;
                    world[npcX][npcY].setNpc(new NPC(random));
                }
            }
        }

        return world;
    }

    @FXML
    private void movePlayerButton() {
        if (!playerEngaged) {
            int moveX = 0;
            int moveY = 0;

            if (random.nextBoolean()) {
                moveX = random.nextBoolean() ? 1 : -1;
            } else {
                moveY = random.nextBoolean() ? 1 : -1;
            }

            int newX = currentX + moveX;
            int newY = currentY + moveY;

            boolean validMove = isValidMove(newX, newY);

            if (validMove) {
                lastMoveDirection = calculateMoveDirection(currentX, currentY, newX, newY);
                currentX = newX;
                currentY = newY;

                Room currentRoom = gameWorld[currentX][currentY];

                if (currentRoom.hasNPC()) {
                    playerEngaged = true;
                    handleNPCEncounter();
                } else {
                    Platform.runLater(() -> updateRoomDescription());
                }
            } else {
                lastMoveDirection = "blocked";
                Platform.runLater(() -> updateRoomDescription());
            }
        }
    }

    private boolean isValidMove(int newX, int newY) {
        return newX >= 0 && newX < gameWorld.length && newY >= 0 && newY < gameWorld[0].length;
    }

    private String calculateMoveDirection(int currentX, int currentY, int newX, int newY) {
        if (newX > currentX) {
            return "down";
        } else if (newX < currentX) {
            return "up";
        } else if (newY > currentY) {
            return "right";
        } else if (newY < currentY) {
            return "left";
        } else {
            return "blocked";
        }
    }

    private void updateRoomDescription() {
        if (currentX >= 0 && currentX < gameWorld.length && currentY >= 0 && currentY < gameWorld[0].length) {
            Room currentRoom = gameWorld[currentX][currentY];
            StringBuilder description = new StringBuilder();
            description.append("You are in a room at position (").append(currentRoom.getLeftRight()).append(", ").append(currentRoom.getUpDown()).append(").\n");

            if (lastMoveDirection != null) {
                description.append("Last Move: ").append(lastMoveDirection).append(". ");
            }

            if (currentRoom.hasNPC()) {
                description.append("An NPC is in the room. Choose your action.\n");
            } else {
                description.append("The room is empty. Choose your action.\n");
            }

            roomDescription.setText(description.toString());
        } else {
            roomDescription.setText("Invalid position in the game world.\n");
        }
    }

    private int roll20SidedDie() {
        return random.nextInt(20) + 1;
    }

    @FXML
    public void fightButton() {
        Room currentRoom = gameWorld[currentX][currentY];

        if (currentRoom.hasNPC() && playerEngaged) {
            NPC npc = currentRoom.getNpc();
            int playerRoll = roll20SidedDie();

            roomDescription.appendText("Player rolled a " + playerRoll + " for attack.\n");

            if (playerRoll >= npc.getDex()) {
                int damage = player.getStr() / 3;
                npc.dmgTaken(damage);

                roomDescription.appendText("You dealt " + damage + " damage to the NPC.\n");

                if (!npc.alive()) {
                    currentRoom.removeNPC();
                    roomDescription.appendText("You defeated the NPC!\n");
                    showOptionsDialog();
                } else {
                    npcAttackPlayer(npc);
                }
            } else {
                roomDescription.appendText("You missed the attack!\n");
                npcAttackPlayer(npc);
            }

            player.updateCharacterStats();
        } else {
            roomDescription.setText("There is no NPC to fight in the room.\nChoose your action.");
        }
    }

    private void npcAttackPlayer(NPC npc) {
        if (player.alive()) {
            int npcRoll = random.nextInt(20) + 1;

            roomDescription.appendText("NPC rolled a " + npcRoll + " for attack.\n");

            if (npcRoll >= player.getDex()) {
                int damage = Math.max(1, npc.getStr() / 3);
                player.takeDmg(damage);

                roomDescription.appendText("NPC dealt " + damage + " damage to you.\n");

                if (!player.alive()) {
                    gameOver();
                }
            } else {
                roomDescription.appendText("NPC missed the attack!\n");
            }
        }
    }

    @FXML
    public void runAwayButton() {
        Room currentRoom = gameWorld[currentX][currentY];

        if (currentRoom.hasNPC() && playerEngaged) {
            NPC npc = currentRoom.getNpc();

            int npcRoll = roll20SidedDie();
            int npcIntelligence = npc.getIntel();

            roomDescription.appendText("NPC rolled a " + npcRoll + " for intelligence.\n");

            if (npcRoll < npcIntelligence) {
                int playerDamage = Math.max(1, npc.getStr() / 3);
                player.takeDmg(playerDamage);
                roomDescription.appendText("The NPC saw you and attacked! You took " + playerDamage + " damage while trying to run away.\n");

                if (!player.alive()) {
                    gameOver();
                    return;
                }
            } else {
                roomDescription.appendText("The NPC did not notice you. You successfully ran away from the NPC!\n");
                currentRoom.removeNPC();
            }

            player.updateCharacterStats();
            playerEngaged = false;
            waitForUserInput = false;
            synchronized (lock) {
                lock.notify();
            }

            if (!player.alive()) {
                gameOver();
            } else {
                roomDescription.appendText("You successfully ran away.");
            }
        } else {
            roomDescription.setText("There is no NPC to run away from.\nChoose your action.");
        }
    }

    private void gameOver() {
        if (npcEncounterHandled && showOptionsDialog) {
            Room currentRoom = gameWorld[currentX][currentY];
            NPC npc = currentRoom.getNpc();
            int npcRoll = random.nextInt(20) + 1;

            roomDescription.appendText("NPC rolled a " + npcRoll + " for attack.\n");

            if (npcRoll >= player.getDex()) {
                int damage = Math.max(1, npc.getStr() / 3);
                player.takeDmg(damage);

                roomDescription.appendText("NPC dealt " + damage + " damage to you.\n");

                if (!player.alive()) {
                    Platform.runLater(this::showGameOverOptionsDialog);
                    return;
                }
            } else {
                roomDescription.appendText("NPC missed the attack!\n");
            }

            npcEncounterHandled = false;
            showOptionsDialog = false;
        }

        Platform.runLater(this::showGameOverOptionsDialog);
    }

    private void showGameOverOptionsDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("You were defeated in battle. Choose your action:");

        ButtonType resetButton = new ButtonType("Reset Game");
        ButtonType quitButton = new ButtonType("Quit Game", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(resetButton, quitButton);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == resetButton) {
                resetGame();
            } else if (buttonType == quitButton) {
                Platform.exit();
            }
        });
    }

    private boolean resettingGame = false;

    private void resetGame() {
        synchronized (lock) {
            resettingGame = true;

            player.setHp(0);
            player.setTotalGold(0);

            currentX = 0;
            currentY = 0;
            playerEngaged = false;
            successfullyEscaped = false;

            gameWorld = createGameWorld();

            Room initialRoom = gameWorld[currentX][currentY];

            if (initialRoom.hasNPC()) {
                initialRoom.removeNPC();
            }

            handleNPCEncounter();

            waitForUserInput = true;
            npcEncounterHandled = false;
            showOptionsDialog = false;

            lastMoveDirection = null;

            Platform.runLater(() -> {
                if (!resettingGame) {
                    roomDescription.setText("");
                }
                lock.notify();
            });

            try {
                lock.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            resettingGame = false;
        }

        updateRoomDescription();
        movePlayerButton();
    }

    @Deprecated
    public void sleepButton(ActionEvent actionEvent) {
        Platform.runLater(() -> roomDescription.setText(""));

        playerEngaged = false;
        player.sleep();
        updateRoomDescription();
    }

    private void handleNPCEncounter() {
        Room currentRoom = gameWorld[currentX][currentY];

        if (currentRoom.hasNPC() && !currentRoom.isBlocked()) {
            roomDescription.appendText("An NPC is in the room. Choose your action.\n");
            npcEncounterHandled = true;
            showOptionsDialog = playerEngaged;
            playerEngaged = true;
        } else {
            NPC npc = new NPC(random);

            while (currentRoom.isBlocked()) {
                int newX = random.nextInt(10);
                int newY = random.nextInt(10);
                currentRoom = gameWorld[newX][newY];
                npc = new NPC(random);
            }

            currentRoom.setNpc(npc);
            roomDescription.setText("While sleeping, you were ambushed by an NPC!\n");
            npcEncounterHandled = false;
            showOptionsDialog = false;
            playerEngaged = true;
        }
        updateRoomDescription();
    }

    private void showOptionsDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Options");
        alert.setHeaderText("You defeated the NPC. Choose your action:");
        alert.setContentText("Choose an option:");

        ButtonType searchForGoldButton = new ButtonType("Search for Gold");
        ButtonType goSleepButton = new ButtonType("Go to Sleep");

        alert.getButtonTypes().setAll(searchForGoldButton, goSleepButton);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == searchForGoldButton) {
                int foundGold = random.nextInt(11) + 5;
                player.addGold(foundGold);
                roomDescription.appendText("You found " + foundGold + " gold in the room!\n");
            } else if (buttonType == goSleepButton) {
                player.sleep();
                roomDescription.appendText("You went to sleep.\n");
            }
            playerEngaged = false;
        });
    }

    private void searchForGold() {
        int playerRoll = roll20SidedDie();
        int intelligence = player.getIntelligence();

        System.out.println("You rolled a " + playerRoll + " for searching.");

        if (playerRoll < intelligence) {
            int foundGold = random.nextInt(11) + 5;
            player.addGold(foundGold);
            roomDescription.appendText("You found " + foundGold + " gold in the room!\n");
        } else {
            roomDescription.appendText("You didn't find any gold in the room.\n");
        }
    }

}