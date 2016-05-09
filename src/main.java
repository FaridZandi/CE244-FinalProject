import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import com.sun.org.apache.xpath.internal.SourceTree;
import oracle.jrockit.jfr.StringConstantPool;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Y50 on 4/15/2016.
 */
public class main {

    public static void main(String[] args) {
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader("ability.txt"));
            try{
                Ability newAbility = loadAbility(br);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GameObjectsHolder gameObjectsHolder = new GameObjectsHolder();
        loadGame(gameObjectsHolder, "gameData.dat");
    }

    public static void loadGame(GameObjectsHolder gameObjectsHolder, String fileName) {

    }


    public static Ability loadAbility(BufferedReader br) throws IOException
    {
        String x;
        String y;
        if((x = br.readLine()) != null)
        {
            if(x.equals("0"))
            {
                Ability ability = new Ability();
                ability.setCastable(false);
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
                    y = br.readLine();
                    PrerequisiteAbility tempPrerequisiteAbility = new PrerequisiteAbility(x , Integer.parseInt(y));
                }
                x = br.readLine();
                ability.setBuffsReplacedEachLevel(Boolean.parseBoolean(x));
                x = br.readLine();
                while((x = br.readLine()) != null)
                {
                    if(x.equals("*"))
                    {
                        break;
                    }
                    y = br.readLine();
                    String[] parts = y.split(" ");
                    Buff temp = new Buff(x, (parts[0]=="0")?false:true,Integer.parseInt(parts[1]) , Integer.parseInt(parts[2]) , Integer.parseInt(parts[3]) ,Integer.parseInt(parts[4]) ,Integer.parseInt(parts[5]) ,Integer.parseInt(parts[6]) ,Integer.parseInt(parts[7]) ,Integer.parseInt(parts[7]));
                    ability.getAffectingBuffsAfterAcquiring().add(temp);
                }
                return ability;
            }
            else if(x.equals("1"))
            {
                CastableAbility ability = new CastableAbility();
                ability.setCastable(true);
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
                    if(x.equals("*")) {
                        break;
                    }
                    y = br.readLine();
                    PrerequisiteAbility tempPrerequisiteAbility = new PrerequisiteAbility(x , Integer.parseInt(y));
                    ability.getPrerequisiteAbilities().add(tempPrerequisiteAbility);
                }
                x = br.readLine();
                ability.setBuffsReplacedEachLevel(Boolean.parseBoolean(x));
                x = br.readLine();
                while((x = br.readLine()) != null)
                {
                    if(x.equals("*"))
                    {
                        break;
                    }
                    y = br.readLine();
                    String[] parts = y.split(" ");
                    Buff temp = new Buff(x, (parts[0]=="0")?false:true,Integer.parseInt(parts[1]) , Integer.parseInt(parts[2]) , Integer.parseInt(parts[3]) ,Integer.parseInt(parts[4]) ,Integer.parseInt(parts[5]) ,Integer.parseInt(parts[6]) ,Integer.parseInt(parts[7]) ,Integer.parseInt(parts[7]));
                    ability.getAffectingBuffsAfterAcquiring().add(temp);
                }
                x = br.readLine();
                ability.setSuccessMessage(x);
                x = br.readLine();
                while ( (x = br.readLine()) != null)
                {
                    if(x.equals("*"))
                    {
                        break;
                    }
                    String[] prices = x.split(" ");
                    Price tempPrice = new Price(Integer.parseInt(prices[0]),Integer.parseInt(prices[1]),Integer.parseInt(prices[2]),Integer.parseInt(prices[3]),Integer.parseInt(prices[4]));
                    ability.getCastPrices().add(tempPrice);
                }
                while((x = br.readLine()) != null)
                {
                    if(x.equals("*"))
                    {
                        break;
                    }
                    String[] parts = x.split(" ");
                    CastableData temp = new CastableData(Integer.parseInt(parts[0]) , Integer.parseInt(parts[1]) ,Integer.parseInt(parts[2]) ,Integer.parseInt(parts[3]) , Integer.parseInt(parts[4]), Double.parseDouble(parts[5]) , Boolean.parseBoolean(parts[6]),Boolean.parseBoolean(parts[7]),Boolean.parseBoolean(parts[8]));
                    ability.getCastableData().add(temp);
                }
                return ability;
            }
        }
        return null;
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
