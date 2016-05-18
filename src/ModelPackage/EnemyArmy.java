package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/5/2016.
 */
public class EnemyArmy {
    private ArrayList<Enemy> soldiers;

    public EnemyArmy()
    {
        soldiers = new ArrayList<>();
    }

    public ArrayList<Enemy> getEnemies() {
        return soldiers;
    }

    public void DoTurn() {
        ArrayList<Soldier> enemies = soldiers.get(0).getOpponentArmy();
        int numberOfEnemies = enemies.size();
        for (Enemy soldier : soldiers) {
            int randomTarget = (int)(Math.random() * numberOfEnemies);
            soldier.attack(enemies.get(randomTarget) , 0);
        }
    }
}