package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/2/2016.
 */
public class CastableAbility extends Ability {

    private ArrayList<Price> castPrices;
    private ArrayList<CastableData> castableData;
    private String successMessage;

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


    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public ArrayList<Price> getCastPrices() {
        return castPrices;
    }

    public ArrayList<CastableData> getCastableData() {
        return castableData;
    }
}
