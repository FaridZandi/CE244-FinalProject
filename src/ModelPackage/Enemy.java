package ModelPackage;

import ViewPackage.View;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Y50 on 5/5/2016.
 */
public class Enemy extends Soldier {

    private EnemyArmy enemyArmy;
    public Enemy(String soldierType, String name, File img)
    {
        super(soldierType, name, new ArrayList<>(), img);
    }

    @Override
    public void getAttacked(int damage) {
        super.getAttacked(damage);
        if(this.getCurrentHealth() == 0)
        {
            this.enemyArmy.getEnemies().remove(this);
            View.show(this.getName() + " was slain!");
            //TODO : an enemy soldier has been killed here, perform necessary things.
        }
    }

    @Override
    public void init(Story story) {
        super.init(story);
        this.enemyArmy = story.getCurrentBattle().getEnemyArmy();
    }
}
