import java.util.Random;
import java.util.ArrayList;

public class EnemyGenerator {
    public static void main(String[] args) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            enemies.add(createRandomEnemy(random));
        }

        for (Enemy enemy : enemies) {
            enemy.attack();
        }
    }

    private static Enemy createRandomEnemy(Random random) {
        int enemyType = random.nextInt(4);
        int weight, height;

        switch (enemyType) {
            case 0:
                weight = random.nextInt(6) + 5;
                height = random.nextInt(31) + 70;
                return new Goblin(weight, height);
            case 1:
                height = random.nextInt(61) + 90;
                return new Ghost(height);
            case 2:
                weight = random.nextInt(81) + 120;
                height = random.nextInt(101) + 200;
                return new Ogre(weight, height);
            case 3:
                weight = random.nextInt(501) + 1000;
                height = random.nextInt(1251) + 750;
                return new Dragon(weight, height);
            default:
                return null;
        }
    }
}
