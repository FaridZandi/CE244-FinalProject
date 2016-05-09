import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Hero extends Soldier{

    private Player player;

    public Hero()
    {

    }

    public boolean payPrice(Price price)
    {
        if(     getCurrentHealth() > price.getHealthPrice()&&
                getCurrentMagic() > price.getMagicPrice() &&
                getEnergyPoints() > price.getEPPrice() &&
                player.getGold() > price.getGoldPrice() &&
                player.getXp() > price.getXPPrice())
        {
            setCurrentMagic(getCurrentMagic() - price.getMagicPrice());
            setCurrentHealth(getCurrentHealth() - price.getHealthPrice());
            setEnergyPoints(getEnergyPoints() - price.getEPPrice());
            player.setGold(player.getGold() - price.getGoldPrice());
            player.setXp(player.getXp() - price.getXPPrice());
            return true;
        }
        else
        {
            return false;
        }
    }

    public void addItem(CastableItem temp) {
        getInventory().add(temp);
    }
}
