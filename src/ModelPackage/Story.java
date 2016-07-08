package ModelPackage;

import ViewPackage.GamePanel;
import ViewPackage.View;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class Story {
    private final GamePanel gamePanel;
    private String endingStory;
    private ArrayList<Battle> battles;
    private int currentBattleNumber;
    private GameObjectsHolder gameObjectsHolder;
    private Shop shop;
    private GameMap gameMap;

    private boolean isGameOver;
    private boolean isInBattle;

    public Story(GamePanel gamePanel, GameObjectsHolder gameObjectsHolder){
        this.gamePanel = gamePanel;
        isInBattle = false;

        this.gameObjectsHolder = gameObjectsHolder;
//        Model.loadBattles(this , "battles.txt");
        shop = new Shop(gamePanel , gameObjectsHolder , "all");

        currentBattleNumber = 0;
        gameMap = new GameMap(gamePanel , gameObjectsHolder);
//        battles.get(currentBattleNumber).init(this);
    }


    public void proceedToNextStage()
    {
        if(!getCurrentBattle().isBattleFinished())
        {
            getCurrentBattle().proceedToNextStage();
        }
        else
        {
            getCurrentBattle().finish();
            if(currentBattleNumber == battles.size() - 1)
            {
                this.finish();
            }
            else
            {
                currentBattleNumber++;
                battles.get(currentBattleNumber).init(this);
                getCurrentBattle().proceedToNextStage();
            }
        }
    }


    private void loseGame()
    {
        View.show("You lost!");
        isGameOver = true;
    }
    private void finish() {
        View.show(endingStory);
        isGameOver = true;
    }

    public Battle getCurrentBattle()
    {
        return battles.get(currentBattleNumber);
    }

    public String getEndingStory() {
        return endingStory;
    }

    public void setEndingStory(String endingStory) {
        this.endingStory = endingStory;
    }

    public boolean isCurrentlyInBattle()
    {
        return true;
    }

    public Shop getShop() {
        return shop;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void checkGameOver() {
        if(gameObjectsHolder.getPlayer().isGameOver())
        {
            loseGame();
        }
    }

    public GameObjectsHolder getGameObjectsHolder() {
        return gameObjectsHolder;
    }

    public void setGameObjectsHolder(GameObjectsHolder gameObjectsHolder) {
        this.gameObjectsHolder = gameObjectsHolder;
    }

    public void setBattles(ArrayList battles) {
        this.battles = battles;
    }

    public boolean getInBattle() {
        return isInBattle;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setInBattle(boolean inBattle) {
        this.isInBattle= inBattle;
    }
}
