package ModelPackage;

import ControlPackage.CreateData;
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
        String toLowerName = name.toLowerCase().trim();
        for (SoldierType soldierType : soldierTypes) {
            if(soldierType.getName().toLowerCase().equals(toLowerName))
            {
                return soldierType;
            }
        }

        for (Hero hero : player.getHeroes()) {
            if(hero.getName().toLowerCase().equals(toLowerName))
            {
                return hero;
            }
            for (Ability ability : hero.getAbilities()) {
                if(ability.getName().toLowerCase().equals(toLowerName))
                {
                    return ability;
                }
            }
        }

        if(player.getCurrentBattle() != null)
        {
            for (Enemy enemy : player.getCurrentBattle().getEnemyArmy().getEnemies()) {
                if(enemy.getName().toLowerCase().equals(toLowerName))
                {
                    return enemy;
                }
                for (Ability ability : enemy.getAbilities()) {
                    if(ability.getName().toLowerCase().equals(toLowerName))
                    {
                        return ability;
                    }
                }
            }
        }

        for (Item item : items) {
            if(item.getName().toLowerCase().equals(toLowerName))
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
                temp.add(Model.deepClone(item , Item.class));
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
