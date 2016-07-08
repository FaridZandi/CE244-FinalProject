package ModelPackage;

import ControlPackage.Control;
import ViewPackage.GamePanel;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Created by Y50 on 5/5/2016.
 */
public class EnemyArmy {
    private ArrayList<Enemy> soldiers;

    private Battle currentBattle;

    public Battle getCurrentBattle() {
        return currentBattle;
    }

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
            if(soldier.calculateAttackDamage(1) != 0)
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

    public void drawUnits(Graphics2D g2d ,Control control) {
        for (int i = 0; i < soldiers.size(); i++) {
            soldiers.get(i).draw(g2d , control);
        }
    }
}