package ModelPackage;

import ViewPackage.View;
import java.util.ArrayList;

/**
 * Created by Y50 on 5/5/2016.
 */
public class Enemy extends Soldier {

    public Enemy(String soldierType, String name)
    {
        super(soldierType, name, new ArrayList<>());
    }

    @Override
    public void getAttacked(int Damage) {
        super.getAttacked(Damage);
        if(this.getCurrentHealth() == 0)
        {
            this.getArmy().remove(this);
            View.show(this.getName() + " was slain!");
            //TODO : an enemy soldier has been killed here, perform necessary things.
        }
    }

}
