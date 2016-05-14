package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class Story {
    private String startingStory;
    private ArrayList<Battle> battles;
    private int currentBattleNumber;
    private GameObjectsHolder gameObjectsHolder;
    private Shop shop;


    public Story(GameObjectsHolder gameObjectsHolder){
        this.gameObjectsHolder = gameObjectsHolder;
        //TODO : see what you can do about this category thing, it's ok for now however.
        shop = new Shop(gameObjectsHolder , "all");
        currentBattleNumber = 0;
        battles = new ArrayList<>();
    }

    public Battle getCurrentBattle()
    {
        return null;
        //return battles.get(currentBattleNumber);
    }

    public String getStartingStory() {
        return startingStory;
    }

    public void setStartingStory(String startingStory) {
        this.startingStory = startingStory;
    }

    public boolean isCurrentlyInBattle()
    {
        return true;
    }

    public Shop getShop() {
        return shop;
    }
}
