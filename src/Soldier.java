import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Soldier extends GameObject{

    private ArrayList<Buff> buffs;
    private ArrayList<Item> inventory;
    public Soldier()
    {
        buffs = new ArrayList<>();
        inventory = new ArrayList<>();
    }




    public void describe()
    {

    }

    public int calculateAttackDamage()
    {

        return 0;
    }

    public int calculateMaximumHealth()
    {

        return 0;
    }

    public int calculateMaximumMagic()
    {

        return 0;
    }

    public int calculateMaximumEnergyPoint()
    {

        return 0;
    }

}