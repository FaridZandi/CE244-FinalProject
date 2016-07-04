package ModelPackage;

import ViewPackage.View;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class Story {
    private String endingStory;
    private ArrayList<Battle> battles;
    private int currentBattleNumber;
    private GameObjectsHolder gameObjectsHolder;
    private Shop shop;
    private GameMap gameMap;

    private boolean isGameOver;
    private boolean isInBattle;

    public Story(GameObjectsHolder gameObjectsHolder){
        gameMap = new GameMap();
        isInBattle = false;

        this.gameObjectsHolder = gameObjectsHolder;
        Model.loadBattles(this , "battles.txt");
        shop = new Shop(gameObjectsHolder , "all");

        currentBattleNumber = 0;
        battles.get(currentBattleNumber).init(this);
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
}
