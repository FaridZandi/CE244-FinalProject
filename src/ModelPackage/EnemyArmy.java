package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/5/2016.
 */
public class EnemyArmy {
    private ArrayList<Enemy> soldiers;

    private Battle currentBattle;

    public EnemyArmy()
    {
        soldiers = new ArrayList<>();
    }

    public ArrayList<Enemy> getEnemies() {
        return soldiers;
    }

    public void doTurn() {
        ArrayList<Soldier> enemies = soldiers.get(0).getOpponentArmy();
        int numberOfEnemies = enemies.size();
        for (Enemy soldier : soldiers)
        {
            if (!currentBattle.getPlayer().isGameOver())
            {
                int randomTarget = (int) (Math.random() * numberOfEnemies);
                soldier.attack(enemies.get(randomTarget), 0);
            }
        }
    }

    public void showEnemyData() {

    }


    public void setCurrentBattle(Battle currentBattle) {
        this.currentBattle = currentBattle;
    }
}