package ModelPackage;

import ViewPackage.View;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Battle {
    private String battleStory;
    private Player player;
    private EnemyArmy enemyArmy;
    private int winningXP;
    private int winningGold;



    private boolean isInHeroesDescriptionStage;
    private boolean isInEnemyDescriptionStage;
    private boolean isInAbilityAcquiringStage;
    private boolean isInShoppingStage;
    private boolean isInFightStage;

    private boolean isBattleFinished;


    public Battle(String battleStory, EnemyArmy enemyArmy, Player player, int winningGold , int winningXP)
    {
        this.battleStory = battleStory;
        this.enemyArmy = enemyArmy;
        enemyArmy.setCurrentBattle(this);
        this.player = player;
        player.setCurrentBattle(this);
        this.winningGold = winningGold;
        this.winningXP = winningXP;

        isInEnemyDescriptionStage = false;
        isInHeroesDescriptionStage = false;
        isInAbilityAcquiringStage = false;
        isInShoppingStage = false;
        isInFightStage = false;

        isBattleFinished = false;
    }

    public void proceedToNextStage()
    {
        if(!isInEnemyDescriptionStage && !isInHeroesDescriptionStage && !isInAbilityAcquiringStage && !isInShoppingStage && !isInFightStage)
        {
            View.show(this.battleStory);
            isInHeroesDescriptionStage = true;
            player.showNameAndTypes();
            return;
        }
        if(isInHeroesDescriptionStage)
        {
            isInHeroesDescriptionStage = false;
            startEnemyDescriptionStage();
            return;
        }
        if(isInEnemyDescriptionStage)
        {
            isInEnemyDescriptionStage = false;
            startAbilityAcquiringStage();
            return;
        }
        if(isInAbilityAcquiringStage)
        {
            isInAbilityAcquiringStage = false;
            startShoppingStage();
            return;
        }
        if(isInShoppingStage)
        {
            isInShoppingStage = false;
            startFightingStage();
            return;
        }
        if(isInFightStage)
        {
            //TODO : Time based stuff must be performed somewhere here.

            for (Enemy enemy : enemyArmy.getEnemies()) {
                enemy.timeBasedPutIntoEffect();
            }
            for (Hero hero : player.getHeroes()) {
                hero.timeBasedPutIntoEffect();
            }

            //TODO : here's must end player's turn.



            //TODO : enemy's Turn must happen here.
            enemyArmy.doTurn();

            return;
        }
    }



    private void startEnemyDescriptionStage() {
        isInEnemyDescriptionStage = true;
        enemyArmy.showEnemyData();
    }

    private void startAbilityAcquiringStage()
    {
        player.showAbilitiesAndLevels();
        isInAbilityAcquiringStage = true;
    }

    private void startShoppingStage()
    {
        isInShoppingStage = true;
    }

    private void startFightingStage()
    {
        isInFightStage = true;
    }









    public void StartEnemyArmyTurn()
    {
        enemyArmy.doTurn();
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

    public Soldier findSoldier(String soldierName) {
        Soldier result;
        result = isInPlayerArmy(soldierName);
        if(result == null)
        {
            result = isInEnemyArmy(soldierName);
        }
        return result;
    }

    public boolean isInAbilityAcquiringStage() {
        return isInAbilityAcquiringStage;
    }

    public boolean isInShoppingStage() {
        return isInShoppingStage;
    }

    public boolean isInEnemyDescriptionStage() {
        return isInEnemyDescriptionStage;
    }

    public boolean isInHeroesDescriptionStage() {
        return isInHeroesDescriptionStage;
    }

    public boolean isBattleFinished() {
        return isBattleFinished;
    }

    public void finish() {
        //TODO : give the winner the rewards.
        this.player.setXp(this.player.getXp() + winningXP);
        this.player.setGold(this.player.getGold() + winningGold);
        View.show("You've been rewarded " + this.winningXP + "XP and " + this.winningGold + " Gold for winning this battle!");
        //TODO : remove temporaryBuffs.
        for (Hero hero : player.getHeroes()) {
            for (Buff buff : hero.getBuffs()) {
                if(!buff.getPermanent())
                {
                    hero.getBuffs().remove(buff);
                }
            }
        }
        //TODO : unset the player's current Battle and EnemyArmy must naturally go away to the hell :))

    }

    public Player getPlayer() {
        return player;
    }

    public void setBattleFinished(Boolean isBattleFinished) {
        this.isBattleFinished = isBattleFinished;
    }
}
