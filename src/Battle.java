import javax.xml.stream.events.EndElement;
import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Battle {
    private String story;
    private Player player;
    private ArrayList<Soldier> enemyArmy;
    private int winningXP;
    private int winningGold;


    public Battle()
    {
        enemyArmy = new ArrayList<>();
    }
}
