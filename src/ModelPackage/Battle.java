package ModelPackage;

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
    private boolean isInShop;
    private boolean isInAbilityAcquiringStage;


    public void StartEnemyArmyTurn()
    {
        enemyArmy.DoTurn();
    }

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

    public ArrayList<Soldier> getTeam(Soldier soldier , boolean isMyTeam)
    {
        String soldierName = soldier.getName();
        ArrayList<Soldier> temp = new ArrayList<>();
        if((isInEnemyArmy(soldierName)!=null) ^ (!isMyTeam))
        {
            ArrayList<Hero> heroes = player.getHeroes();
            for (Hero hero : heroes) {
                temp.add(hero);
            }
            return temp;
        }
        else if((isInPlayerArmy(soldierName)!=null) ^ (!isMyTeam))
        {
            ArrayList<Enemy> enemies = enemyArmy.getEnemies();
            for (Enemy enemy : enemies) {
                temp.add(enemy);
            }
            return temp;
        }
        return null;
    }

    private Soldier isInEnemyArmy(String soldierName)
    {
        ArrayList<Enemy> enemies = enemyArmy.getEnemies();
        for (Enemy enemy : enemies) {
            if(enemy.getName().equals(soldierName))
            {
                return enemy;
            }
        }
        return null;
    }

    private Soldier isInPlayerArmy(String soldierName)
    {
        ArrayList<Hero> heroes = player.getHeroes();
        for (Hero hero : heroes) {
            if(hero.getName().equals(soldierName))
            {
                return hero;
            }
        }
        return null;
    }

    public Soldier findSoldier(String casterName) {
        Soldier result = null;
        result = isInPlayerArmy(casterName);
        if(result == null)
        {
            result = isInEnemyArmy(casterName);
        }
        return result;
    }

    public void startAbilityAcquiring()
    {
        isInAbilityAcquiringStage = true;
    }

    public void startShopping()
    {
        isInShop = true;
    }

    public void startFight()
    {

    }

    public boolean isInAbilityAcquiringStage() {
        return isInAbilityAcquiringStage;
    }

    public boolean isInShop() {
        return isInShop;
    }

}
