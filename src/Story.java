import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class Story {
    private String startingStory;
    private ArrayList<Battle> battles;
    private int currentBattleNumber;
    public Story(){
        currentBattleNumber = 0;
        battles = new ArrayList<>();
    }

    public Battle getCurrentBattle()
    {
        return battles.get(currentBattleNumber);
    }

    public String getStartingStory() {
        return startingStory;
    }

    public void setStartingStory(String startingStory) {
        this.startingStory = startingStory;
    }
}
