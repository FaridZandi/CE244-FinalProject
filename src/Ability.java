import java.util.ArrayList;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Ability extends GameObject {

    private boolean isCastable;
    private ArrayList<Price> castPrices;
    private ArrayList<Price> AcquirePrices;

    public Ability()
    {
        castPrices = new ArrayList<>();
        AcquirePrices = new ArrayList<>();

    }

    public void describe()
    {

    }
}
