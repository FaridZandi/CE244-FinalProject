package ModelPackage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Y50 on 5/2/2016.
 */
public class GameObjectsHolder {
    ArrayList<Item> items;
    ArrayList<SoldierType> soldierTypes;
    private Player player;

    public GameObjectsHolder()
    {
        soldierTypes = new ArrayList<>();
        items = new ArrayList<>();
        player = new Player();
    }

    public GameObject find(String name)
    {
        for (SoldierType soldierType : soldierTypes) {
            if(soldierType.getName().equals(name))
            {
                return soldierType;
            }
        }

        for (Hero hero : player.getHeroes()) {
            if(hero.getName().equals(name))
            {
                return hero;
            }
            for (Ability ability : hero.getAbilities()) {
                if(ability.getName().equals(name))
                {
                    return ability;
                }
            }
        }

        if(player.getCurrentBattle() != null)
        {
            for (Enemy enemy : player.getCurrentBattle().getEnemyArmy().getEnemies()) {
                if(enemy.getName().equals(name))
                {
                    return enemy;
                }
            }
        }

        for (Item item : items) {
            if(item.getName().equals(name))
            {
                return item;
            }
        }
        return null;
    }
    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<Item> items)
    {
        this.items = items;
    }

    public ArrayList<Item> getItemsWithCategory(String category)
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSoldierTypes(ArrayList soldierTypes) {
        this.soldierTypes = soldierTypes;
    }
}
