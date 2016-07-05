package ModelPackage;


import ViewPackage.GamePanel;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Created by Y50 on 4/15/2016.
 */
public class Model
{


    private Story story;
    private GamePanel gamePanel;

    public Model() {
        GameObjectsHolder gameObjectsHolder = new GameObjectsHolder();
        loadGame(gameObjectsHolder);
        story = new Story(gameObjectsHolder);

        for (Hero hero : story.getGameObjectsHolder().getPlayer().getHeroes()) {
            hero.init(story);
        }
    }


    public void loadGame(GameObjectsHolder gameObjectsHolder) {
        loadItems(gameObjectsHolder, "items.txt");
        loadSoldierTypes(gameObjectsHolder, "soldierTypes.txt");
        loadHeroes(gameObjectsHolder, "heroes.txt");
    }

    public static void loadBattles(Story story, String fileName) {
        story.setBattles((ArrayList<Battle>) decodeObject(readFromFile(fileName)));
    }

    private void loadHeroes(GameObjectsHolder gameObjectsHolder, String fileName) {
        gameObjectsHolder.getPlayer().setHeroes((ArrayList<Hero>) decodeObject(readFromFile(fileName)));
    }

    private void loadSoldierTypes(GameObjectsHolder gameObjectsHolder, String fileName) {
        gameObjectsHolder.setSoldierTypes((ArrayList<SoldierType>) decodeObject(readFromFile(fileName)));
    }

    private void loadItems(GameObjectsHolder gameObjectsHolder, String fileName) {

        gameObjectsHolder.setItems((ArrayList<Item>) decodeObject(readFromFile(fileName)));
    }

    public static Object deepClone(Object object) {
        return decodeObject(encodeObject(object));
    }

    public static Object decodeObject(String s) {
        byte[] data = Base64.getDecoder().decode(s);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o = objectInputStream.readObject();
            objectInputStream.close();
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeObject(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readFromFile(String fileName) {
        try {
            String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeInFile(String fileName, String s) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            byte[] shit = s.getBytes();
            fileOutputStream.write(shit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Story getStory() {
        return story;
    }

    public void init(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void update(double v) {
        Player player = this.getStory().getGameObjectsHolder().getPlayer();
        player.walk(gamePanel.getControl());
    }
}

