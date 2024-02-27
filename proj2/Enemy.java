import java.util.ArrayList;
import java.util.Random;

abstract class Enemy {
    private int weight;
    private int height;

    public Enemy(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

     public int getWeight() {
         return weight;
     }

     public void setWeight(int weight) {
         this.weight = weight;
     }

     public int getHeight() {
         return height;
     }

     public void setHeight(int height) {
         this.height = height;
     }

     public void attack() {

     }
 }