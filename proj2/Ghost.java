public class Ghost extends Enemy {
    public Ghost(int height) {
        super(0, height);
    }

    @Override
    public void attack() {
        System.out.println("Boo!");
    }
}