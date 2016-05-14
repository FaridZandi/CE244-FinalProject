package ModelPackage;

import java.util.ArrayList;

/**
 * Created by Y50 on 5/5/2016.
 */
public class EnemyArmy {
    private ArrayList<Enemy> soldiers;

    public EnemyArmy()
    {
        soldiers = new ArrayList<>();
    }

    public ArrayList<Enemy> getEnemies() {
        return soldiers;
    }
}