package ViewPackage;

import ControlPackage.Control;
import ModelPackage.GameMap;
import ModelPackage.Player;
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
    public static final int ScreenHeight = 1000;
    private Control control;
    private JFrame frame;
    public void init(Control control) {
        frame = new JFrame("Amazing Brick");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("2leftarrow.png").getImage());
        frame.getContentPane().add(this);

        frame.setSize(1500, 1000);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.GRAY);

        this.control = control;
        this.setPreferredSize(new Dimension(1500 , 1000));
        this.setLocation(0 , 0);
        this.setBounds(0 , 0 , 1500 , 1000);
        this.setBackground(Color.WHITE);

        frame.addKeyListener(control);

        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);

        GameMap gameMap = control.getModel().getStory().getGameMap();
        Player player = control.getModel().getStory().getGameObjectsHolder().getPlayer();

        if (!this.control.getModel().getStory().getInBattle()) {

            gameMap.draw(g2d , player);
            player.draw(g2d);
        }
        else
        {

        }
        g2d.setColor(Color.BLACK);
        g2d.fillRect(ScreenWidth * 2 / 3 , 0 , ScreenWidth /3 , ScreenHeight);

    }


    public JFrame getFrame() {
        return frame;
    }

    public Control getControl() {
        return control;
    }
}
