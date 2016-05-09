import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Y50 on 4/15/2016.
 */
public class main {

    public static void main(String[] args) {
        System.out.println("Hello");
        loadAbility();
        GameObjectsHolder gameObjectsHolder = new GameObjectsHolder();
        loadGame(gameObjectsHolder, "gameData.dat");
    }

    public static void loadGame(GameObjectsHolder gameObjectsHolder, String fileName) {

    }


    public static void loadAbility()
    {
        Ability ability = null;
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader("ability.txt"));
            try{
                String x;
                if((x = br.readLine()) != null)
                {

                    boolean isCastable = false;
                    if(x.equals("0"))
                    {
                        ability = new Ability();
                        isCastable = false;
                    }
                    else if(x.equals("1"))
                    {
                        ability = new CastableAbility();
                        isCastable = true;
                    }
                    ability.setCastable(isCastable);
                }
                x = br.readLine();
                x = br.readLine();
                ability.setName(x);
                x = br.readLine();
                x = br.readLine();
                ability.setAbilityInfo(x);
                x = br.readLine();
                while ( (x = br.readLine()) != null)
                {
                    if(x.equals("*"))
                    {
                        break;
                    }
                    String[] prices = x.split(" ");
                    Price tempPrice = new Price(Integer.parseInt(prices[0]),Integer.parseInt(prices[1]),Integer.parseInt(prices[2]),Integer.parseInt(prices[3]),Integer.parseInt(prices[4]));
                    ability.getAcquirePrices().add(tempPrice);
                }
                while ( (x = br.readLine()) != null)
                {
                    if(x.equals("*"))
                    {
                        break;
                    }
                    String[] parts = x .split(" ");
                    if(parts[0].equals("0"))
                    {
                        ability.getPrerequisiteAbilities().add(null);
                        //TODO : I'm getting a nullpointerexception here , make it right

                    }
                    else
                    {
                        PrerequisiteAbility tempPrerequisiteAbility = new PrerequisiteAbility(parts[0] , Integer.parseInt(parts[1]));
                    }
                }
                x = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Object deepClone(Object object)
    {
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        }
        catch (Exception e)
        {
            return null;
        }
    }

}
