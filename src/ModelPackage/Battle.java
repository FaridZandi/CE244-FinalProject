package ModelPackage;
import ViewPackage.GamePanel;
import ViewPackage.View;
import java.awt.Graphics2D;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.SwingWorker;


public class Battle extends GameMapCell implements Serializable{
    private String battleStory;
    private Player player;
    private String EnemyInfo;
    private EnemyArmy enemyArmy;
    private int winningXP;
    private int winningGold;



    private boolean isInHeroesDescriptionStage;
    private boolean isInEnemyDescriptionStage;
    private boolean isInAbilityAcquiringStage;
    private boolean isInShoppingStage;
    private boolean isInFightStage;

    private boolean isBattleFinished;

    public Battle(GamePanel gamePanel , String battleStory, String enemyInfo, int winningXP, int winningGold) {
        super(gamePanel);
        this.battleStory = battleStory;
        EnemyInfo = enemyInfo;
        this.winningXP = winningXP;
        this.winningGold = winningGold;
    }

    public void init(Story story)
    {
        createEnemyArmyFromInfo(story);
        this.player = story.getGameObjectsHolder().getPlayer();
        player.setCurrentBattle(this);
        isInEnemyDescriptionStage = false;
        isInHeroesDescriptionStage = false;
        isInAbilityAcquiringStage = false;
        isInShoppingStage = false;
        isInFightStage = false;
        isBattleFinished = false;
        System.out.println("start Shod!");
        enemyArmy.setCurrentBattle(this);


        for (int i = 0; i < player.getHeroes().size(); i++) {
            Hero hero = player.getHeroes().get(i);
            hero.setLocationX(GamePanel.ScreenWidth / 2);
            hero.setLocationY(GamePanel.ScreenHeight / player.getHeroes().size() * i);
            hero.setDirection(Player.West);
        }

        for (int i = 0; i < enemyArmy.getEnemies().size(); i++) {
            Enemy enemy = enemyArmy.getEnemies().get(i);
            enemy.setLocationX(0);
            enemy.setLocationY(GamePanel.ScreenHeight / enemyArmy.getEnemies().size() * i);
            enemy.setDirection(Player.East);
        }


        player.getHeroes().get(0).getAbilities().get(2).acquire(player.getHeroes().get(0));
        player.getHeroes().get(0).getAbilities().get(1).acquire(player.getHeroes().get(0));
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
            //TODO : here's must end player's turn.



            //TODO : enemy's Turn must happen here.
            for (Enemy enemy : enemyArmy.getEnemies()) {
                enemy.timeBasedPutIntoEffect();
            }

            enemyArmy.doTurn();

            for (Hero hero : player.getHeroes()) {
                hero.timeBasedPutIntoEffect();
            }
        }
    }




    public void createEnemyArmyFromInfo(Story story)
    {
        enemyArmy = new EnemyArmy();
        String[] groups = EnemyInfo.split("-");
        for (int i = 0; i < groups.length; i++) {
            groups[i] = groups[i].replaceAll("[ ]{2,}" , " ");
            groups[i] = groups[i].trim();
//            System.out.println(groups[i]);
        }
        for (String group : groups) {
            String[] parts = group.split(" " , 2);
            int number = Integer.parseInt(parts[0]);
            String type = parts[1];
            if(number > 1)
            {
                type = type.substring(0 , type.length() - 1);
            }
            for (int i = 0; i < number; i++) {
                String name;
                if(number == 1)
                {
                    name = type;
                }
                else
                {
                    name = type + " " + i;
                }
                //TODO : get Image Name For Enemies based On the Type of Them And Replace The Null
                File img = null;
                switch (type.toLowerCase())
                {
                    case "weak thug":
                        img = new File("weakThug.png");
                        break;
                    case "able thug":
                        img = new File("ableThug.png");
                        break;
                    case "mighty thug":
                        img = new File("mightyThug.png");
                        break;
                    case "weak angel":
                        img = new File("weakAngel.png");
                        break;
                    case "able angel":
                        img = new File("ableAngel.png");
                        break;
                    case "mighty angel":
                        img = new File("mightyAngel.png");
                        break;
                    case "weak tank":
                        img = new File("weakTank.png");
                        break;
                    case "able tank":
                        img = new File("ableTank.png");
                        break;
                    case "mighty tank":
                        img = new File("mightyTank.png");
                        break;
                }
                Enemy e = new Enemy(type , name, img, enemyArmy);
                e.init(getGamePanel() ,story);
                enemyArmy.getEnemies().add(e);
            }
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

    public boolean isAnyEnemyAlive()
    {
        return enemyArmy.getEnemies().size() != 0;
    }

    public EnemyArmy getEnemyArmy() {
        return enemyArmy;
    }

    public ArrayList<Soldier> getTeam(Soldier soldier , boolean isMyTeam)
    {
        String soldierName = soldier.getName();
        ArrayList<Soldier> temp = new ArrayList<>();
        if((isInEnemyArmy(soldierName)!=null) ^ (isMyTeam))
        {
            ArrayList<Hero> heroes = player.getHeroes();
            temp.addAll(heroes);
            return temp;
        }
        else if((isInPlayerArmy(soldierName)!=null) ^ (isMyTeam))
        {
            ArrayList<Enemy> enemies = enemyArmy.getEnemies();
            temp.addAll(enemies);
            return temp;
        }
        return null;
    }

    private Soldier isInEnemyArmy(String soldierName)
    {
        ArrayList<Enemy> enemies = enemyArmy.getEnemies();
        for (Enemy enemy : enemies) {
            if(enemy.getName().toLowerCase().equals(soldierName.toLowerCase()))
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
            if(hero.getName().toLowerCase().equals(soldierName.toLowerCase()))
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

    public boolean isInFightStage()
    {
        return isInFightStage;
    }

    public boolean isBattleFinished() {
        return isBattleFinished;
    }

    public void finish() {
        this.player.setXp(this.player.getXp() + winningXP);
        this.player.setGold(this.player.getGold() + winningGold);
        if(winningGold != 0 || winningXP!=0) {
            View.show("You've been rewarded " + this.winningXP + "XP and " + this.winningGold + " Gold for winning this battle!");
        }
        //remove temporaryBuffs.
        //TODO : this part has to be tested exclusively !!
        for (Hero hero : player.getHeroes()) {
            int buffsNumber =  hero.getBuffs().size();
            for (int i = 0; i < buffsNumber; i++) {
                if(!hero.getBuffs().get(i).isPermanent())
                {
                    hero.removeBuff(hero.getBuffs().get(i).getName());
                    i--;
                    buffsNumber--;
                }
            }
        }
        //TODO : unset the player's current Battle and EnemyArmy must naturally go away to the hell :))
        this.player.setCurrentBattle(null);
        for (Hero hero : this.player.getHeroes()) {
            for (Ability ability : hero.getAbilities()) {
                if(ability.isCastable())
                {
                    ((CastableAbility)ability).setTurnsToUseAgain(0);
                }
            }
            for (Item item : hero.getInventory()) {
                if(item.isCastable())
                {
                    ((CastableItem)item).setTurnsToUseAgain(0);
                }
            }
        }
        this.enemyArmy = null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setBattleFinished(boolean battleFinished) {
        isBattleFinished = battleFinished;
    }

    @Override
    public void draw(int cornerX, int cornerY, Graphics2D g2d) {

        super.drawTile(cornerX, cornerY, g2d , GameMapCell.battleMapCellImage , true);
    }

    @Override
    public void enter(Story story) {

        new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception {
                ShowBigCircle();

                getGamePanel().getDrawables().clear();
                story.setInBattle(true);
                init(story);
                getGamePanel().getDrawables().addAll(getGamePanel().getControl().getModel().getStory().getGameObjectsHolder().getPlayer().getCurrentBattle().getEnemyArmy().getEnemies());
                getGamePanel().getDrawables().addAll(getGamePanel().getControl().getModel().getStory().getGameObjectsHolder().getPlayer().getHeroes());
                return null;
            }
        }.execute();
    }



    @Override
    public void exit() {

    }
}
