package ControlPackage.CompoundMethods;

import ControlPackage.CompoundMethod;
import ControlPackage.Control;
import ModelPackage.Enemy;
import ModelPackage.Hero;
import ModelPackage.Price;
import ModelPackage.Soldier;
import ViewPackage.View;

/**
 * Created by Y50 on 5/12/2016.
 */
public class Attacker implements CompoundMethod
{
    Control control;
    public Attacker(Control control)
    {
        this.control = control;
    }
    @Override
    public void performMethod(String input) {
        int attackIndex = input.indexOf("attack");
        String attackerName = input.substring(0 , attackIndex - 1);
        String targetName = input.substring(attackIndex + 7);

        Soldier attackerSoldier = control.getModel().getStory().getCurrentBattle().findSoldier(attackerName);
        Soldier target = control.getModel().getStory().getCurrentBattle().findSoldier(targetName);

        if(attackerSoldier.getClass() == Enemy.class)
        {
            View.show("You can't order your enemies to attack, please try again");
            return;
        }

        if(attackerSoldier == null || target == null)
        {
            View.show("No soldier with this name was found, please try again.");
            return;
        }

        Hero attacker = (Hero)attackerSoldier;

        if(control.getModel().getStory().getCurrentBattle().getTeam(attacker , true).contains(target))
        {
            View.show("Cannot attack a unit from own team");
            return;
        }

        Price attackingPrice = new Price(0,0,2,0,0);
        if(!attacker.payPrice(attackingPrice , true))
        {
            View.show("You don't have enough energy points for attacking the target, please try again.");
            return;
        }

        attacker.attack(target , 0);
        if(!control.getModel().getStory().getCurrentBattle().isAnyEnemyAlive())
        {
            control.getModel().getStory().getCurrentBattle().setBattleFinished(true);
            control.getModel().getStory().proceedToNextStage();
        }
    }
}
