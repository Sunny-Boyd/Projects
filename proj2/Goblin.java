public class Goblin extends Enemy {
    public Goblin(int weight, int height) {
        super(weight, height);
    }

    @Override
    public void attack() {
        System.out.println("Gurgle!");
    }
}