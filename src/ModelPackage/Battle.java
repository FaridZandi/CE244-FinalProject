package ModelPackage;

import ControlPackage.Drawable;
import ViewPackage.GamePanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Graphics2D;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;


public class Battle extends GameMapCell implements Serializable{
    private String battleStory;
    private Player player;
    private String EnemyInfo;
    private EnemyArmy enemyArmy;
    private int winningXP;
    private int winningGold;
    public JPanel battleButtons;


    ArrayList<Drawable> drawablesBackUp;


    private boolean isBattleFinished;
    public boolean isShowingOtherTeamAttackAnimation;

    public Battle(GamePanel gamePanel , String battleStory, String enemyInfo, int winningXP, int winningGold) {
        super(gamePanel);
        this.battleStory = battleStory;
        EnemyInfo = enemyInfo;
        this.winningXP = winningXP;
        this.winningGold = winningGold;
        isShowingOtherTeamAttackAnimation = false;
    }

    public void init(Story story)
    {
        createEnemyArmyFromInfo(story);
        this.player = story.getGameObjectsHolder().getPlayer();
        player.setCurrentBattle(this);
        isBattleFinished = false;
        System.out.println("start Shod!");
        enemyArmy.setCurrentBattle(this);


        for (int i = 0; i < player.getHeroes().size(); i++) {
            Hero hero = player.getHeroes().get(i);
            hero.setLocationX((int)(GamePanel.ScreenWidth * (i / (5.0 * player.getHeroes().size()) + 0.43)));
            hero.setLocationY((int)(GamePanel.ScreenWidth * (i / (6.5 * player.getHeroes().size()) + 0.32)));
            hero.setDirection(Player.West);
        }

        for (int i = 0; i < enemyArmy.getEnemies().size(); i++) {
            Enemy enemy = enemyArmy.getEnemies().get(i);
            enemy.setLocationX((int)(GamePanel.ScreenWidth * (-i / (5.0 * player.getHeroes().size()) + 0.13)));
            enemy.setLocationY((int)(GamePanel.ScreenWidth * (i / (6.5 * player.getHeroes().size()) + 0.32)));
            enemy.setDirection(Player.East);
        }


        player.getHeroes().get(1).getAbilities().get(2).acquire(player.getHeroes().get(1));
        player.getHeroes().get(1).getAbilities().get(0).acquire(player.getHeroes().get(1));
    }

    public void proceedToNextStage()
    {
        //TODO : here's must end player's turn.



        //TODO : enemy's Turn must happen here.


        new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception {
                isShowingOtherTeamAttackAnimation = true;
                battleButtons.setVisible(false);
                for (Enemy enemy : enemyArmy.getEnemies()) {
                    enemy.timeBasedPutIntoEffect();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                enemyArmy.doTurn();
                return null;
            }
        }.execute();



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

    public boolean isBattleFinished() {
        return isBattleFinished;
    }

    public void finish(Story story) {
    new SwingWorker()
    {
        @Override
        protected Object doInBackground() throws Exception {
            story.setInBattle(false);
            enemyArmy = null;
            player.setCurrentBattle(null);




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
            for (Hero hero : player.getHeroes()) {
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

            player.setXp(player.getXp() + winningXP);
            player.setGold(player.getGold() + winningGold);

            String text = "You've been rewarded " + winningXP + " XP and " + winningGold + " Gold for winning this battle!";


            new ProceedMenu(getGamePanel() , text , () -> {
                battleButtons.setVisible(false);
                getGamePanel().getFrame().remove(battleButtons);

                getGamePanel().getDrawables().clear();
                getGamePanel().getDrawables().addAll(drawablesBackUp);
                MyCircle circle = new MyCircle(0 , GamePanel.ScreenWidth / 3 , GamePanel.ScreenHeight / 2, Color.RED);
                getGamePanel().getDrawables().add(circle);
                implodeBigCircle(circle);
                getGamePanel().removeDrawable(circle);
                getGamePanel().resetKeyboardListener();

            });
            return null;
        }
    }.execute();


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
                init(story);
                story.setInBattle(true);

                drawablesBackUp = new ArrayList<>();
                drawablesBackUp.addAll(getGamePanel().getDrawables());

                MyCircle circle = new MyCircle(0 , GamePanel.ScreenWidth / 3 , GamePanel.ScreenHeight / 2, Color.RED);
                getGamePanel().getDrawables().add(circle);
                explodeBigCircle(circle);

                getGamePanel().getDrawables().clear();
                BackGroundImage backGroundImage = new BackGroundImage(new File("Flame's_Battle_Stage.png") , 0 , 0 , GamePanel.ScreenWidth * 2 /3 , GamePanel.ScreenHeight);
                getGamePanel().getDrawables().add(backGroundImage);
                getGamePanel().getDrawables().addAll(getGamePanel().getControl().getModel().getStory().getGameObjectsHolder().getPlayer().getCurrentBattle().getEnemyArmy().getEnemies());
                getGamePanel().getDrawables().addAll(getGamePanel().getControl().getModel().getStory().getGameObjectsHolder().getPlayer().getHeroes());


                battleButtons = new JPanel();
                battleButtons.setPreferredSize(new Dimension(1500, 1000));
                battleButtons.setBounds(0 , 0 , 1500 ,1000 );

                battleButtons.setBackground(Color.GRAY);
                battleButtons.setLayout(null);

                JButton doneButton = new JButton("done");
                doneButton.setBounds(1100, 100 , 200 ,100);
                doneButton.addActionListener(e -> {
                    for (Component component : getGamePanel().getComponents()) {
                        getGamePanel().remove(component);
                    }
                    proceedToNextStage();
                });
                battleButtons.add(doneButton);
                getGamePanel().getFrame().add(battleButtons);
                battleButtons.setVisible(true);
                new ProceedMenu(getGamePanel() , battleStory , () -> {});
                return null;
            }
        }.execute();
    }


    @Override
    public void exit() {

    }
}
