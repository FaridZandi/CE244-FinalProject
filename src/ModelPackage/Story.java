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

    private boolean isGameOver;

    public Story(GameObjectsHolder gameObjectsHolder){
        this.gameObjectsHolder = gameObjectsHolder;
        //TODO : see what you can do about this category thing, it's ok for now however.
        shop = new Shop(gameObjectsHolder , "all");
        currentBattleNumber = 0;
        battles = new ArrayList<>();
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
}
