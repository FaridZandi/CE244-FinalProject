import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class GameObjectsHolder {
    ArrayList<GameObject> items;
    ArrayList<SoldierType> soldierTypes;
    Player player;

    public GameObjectsHolder()
    {
        soldierTypes = new ArrayList<>();
        items = new ArrayList<>();
        player = new Player();
    }

    public GameObject find(String name)
    {

        return null;
    }


}
