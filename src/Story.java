import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class Story {
    private String startingStory;
    private ArrayList<Battle> battles;

    public Story(){
        battles = new ArrayList<>();
    }

    public Battle getCurrentBattle()
    {
        //TODO: make this right!
        return new Battle();
    }
}
