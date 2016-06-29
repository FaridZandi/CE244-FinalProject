package ViewPackage;

import ControlPackage.Control;
import ModelPackage.GameMapCell;
import ModelPackage.Model;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

/**
 * Created by Y50 on 6/1/2016.
 */
public class GamePanel extends JPanel
{

    public static final int ScreenWidth = 1500;
    public static final int ScreenHight = 1000;
    public static final int CellSize = 200;
    private Model model;
    private Control control;
    private JFrame frame;
    public void init(Control control, Model model) {
        frame = new JFrame("Amazing Brick");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("2leftarrow.png").getImage());
        frame.getContentPane().add(this);
        this.model = model;
        this.control = control;

        frame.setSize(1500, 1000);
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(1500 , 1000));
        this.setLocation(0 , 0);
        this.setBounds(0 , 0 , 1500 , 1000);
        this.setBackground(Color.WHITE);
        frame.getContentPane().setBackground(Color.GRAY);

        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double playerX = control.getModel().getStory().getGameObjectsHolder().getPlayer().getLocationX();
        double playerY = control.getModel().getStory().getGameObjectsHolder().getPlayer().getLocationY();
        ArrayList<ArrayList<GameMapCell>> temp = control.getModel().getStory().getGameMap().getGameMapCells();
        for (ArrayList<GameMapCell> gameMapCells : temp) {
            for (GameMapCell gameMapCell : gameMapCells) {
                gameMapCell.draw(0 , 0 , g);
            }
        }
    }


    public JFrame getFrame() {
        return frame;
    }
}
