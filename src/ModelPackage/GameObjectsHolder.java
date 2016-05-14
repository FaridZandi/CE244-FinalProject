package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class GameObjectsHolder {
    ArrayList<Item> items;
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

    public ArrayList<Item> getItems(String category)
    {
        ArrayList<Item> temp = new ArrayList<>();
        for (Item item : items) {
            if(item.getCategory().equals(category))
            {
                temp.add((Item)Model.deepClone(item));
            }
        }
        return temp;
    }


    public Player getPlayer() {
        return player;
    }
}
