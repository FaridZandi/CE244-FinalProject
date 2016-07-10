package ModelPackage;

import ViewPackage.GamePanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.SwingWorker;

/**
 * Created by Y50 on 7/10/2016.
 */
public class ProceedMenu
{

    static Image backgoundImage;

    static {
        File file = new File("messagebox-background.png");
        try {
            backgoundImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        backgoundImage = backgoundImage.getScaledInstance(GamePanel.ScreenWidth / 2 , GamePanel.ScreenHeight / 4 , Image.SCALE_DEFAULT);
    }
    public ProceedMenu(GamePanel gamePanel ,String battleStory, myFunction toBePerformedMethod)
    {
        BackGroundImage bgi = new BackGroundImage(new File("messageBox Background.png") , GamePanel.ScreenWidth / 12 , GamePanel.ScreenHeight / 3, GamePanel.ScreenWidth / 2 , GamePanel.ScreenHeight / 3 );
        gamePanel.getDrawables().add(bgi);
        MyText myText = new MyText(battleStory ,  GamePanel.ScreenWidth / 12 + 20 , GamePanel.ScreenHeight / 3 + 20, GamePanel.ScreenWidth / 2 - 40 , GamePanel.ScreenHeight / 4 - 20 ,40);
        gamePanel.getDrawables().add(myText);

//        MyText.moveUp(myText);

        JButton proceedButton = new JButton("continue");
        proceedButton.setBounds(GamePanel.ScreenWidth / 3 - 75 , GamePanel.ScreenHeight * 2 / 3 - 95 , 150 , 75);
        gamePanel.add(proceedButton);
        proceedButton.addActionListener(e -> new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception {
                gamePanel.remove(proceedButton);
//                MyText.moveUp(myText);
                gamePanel.removeDrawable(myText);
                gamePanel.removeDrawable(bgi);
                toBePerformedMethod.perform();
                return null;
            }
        }.execute());
    }
}
