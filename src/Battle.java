import javax.xml.stream.events.EndElement;
import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Battle {
    private String story;
    private Player player;
    private EnemyArmy enemyArmy;
    private int winningXP;
    private int winningGold;


    public boolean isAnyEnemyAlive()
    {
        if(enemyArmy.getEnemies().size() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Battle()
    {
        enemyArmy = new EnemyArmy();
    }

    public EnemyArmy getEnemyArmy() {
        return enemyArmy;
    }

    public ArrayList<Soldier> getTeam(Soldier soldier)
    {
        ArrayList<Soldier> temp = new ArrayList<>();
        if(isInEnemyArmy(soldier))
        {
            ArrayList<Hero> heroes = player.getHeroes();
            for (Hero hero : heroes) {
                temp.add(hero);
            }
            return temp;
        }
        else if(isInPlayerArmy(soldier))
        {
            ArrayList<Enemy>  enemies = enemyArmy.getEnemies();
            for (Enemy enemy : enemies) {
                temp.add(enemy);
            }
            return temp;
        }
        return null;
    }

    private boolean isInEnemyArmy(Soldier soldier)
    {
        ArrayList<Enemy> enemies = enemyArmy.getEnemies();
        for (Enemy enemy : enemies) {
            if(soldier.equals(enemy))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isInPlayerArmy(Soldier soldier)
    {
        ArrayList<Hero> heroes = player.getHeroes();
        for (Hero hero : heroes) {
            if(hero.equals(soldier))
            {
                return true;
            }
        }
        return false;
    }
}
