package ControlPackage;

import ControlPackage.Control;
import ControlPackage.CreateData;
import ModelPackage.Model;
import ViewPackage.GamePanel;
import javax.swing.SwingUtilities;

/**
 * Created by Y50 on 6/1/2016.
 */
public class ChildrenOfTime
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        GamePanel gamePanel = new GamePanel();
        CreateData.writeItems();
        CreateData.writeHeroes();
        CreateData.writeSoldierTypes();
        CreateData.writeBattles();
        Control control = new Control(gamePanel);
        control.getModel().getStory().proceedToNextStage();
        gamePanel.init(control , control.getModel());
        control.start();
    }


}
