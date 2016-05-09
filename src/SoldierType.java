import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class SoldierType extends GameObject{

    private ArrayList<Ability> abilities;
    private int attackPower;
    private int maximumHealth;
    private int maximumMagic;
    private int inventorySize;
    private int healthRefillRatePercentage;
    private int magicRefillRatePercentage;

    public SoldierType()
    {
        abilities = new ArrayList<>();
    }
    public void describe()
    {

    }
}

