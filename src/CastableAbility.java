import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class CastableAbility extends Ability {

    private ArrayList<Price> castPrices;
    private ArrayList<CastableData> castableData;

    private int turnsToUseAgain;

    public CastableAbility()
    {
        castPrices = new ArrayList<>();
        castableData = new ArrayList<>();
    }

    public void cast(Soldier target)
    {
        System.out.println("hello there");
    }


}
