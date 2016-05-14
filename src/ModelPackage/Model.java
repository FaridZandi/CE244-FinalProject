package ModelPackage;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Y50 on 4/15/2016.
 */
public class Model {



    private GameObjectsHolder gameObjectsHolder;
    private Story story;

    public Model(){
        gameObjectsHolder = new GameObjectsHolder();
        loadGame(gameObjectsHolder , "gameData.txt");
        story = new Story(gameObjectsHolder);
    }


    public static void loadGame(GameObjectsHolder gameObjectsHolder, String fileName) {

    }

    public static ArrayList<Ability> loadAbility()
    {
        Ability ability = null;
        BufferedReader br;
        ArrayList<Ability> abilities = new ArrayList<>();
        Map mapDataAbility = new HashMap<String,ArrayList<String>>();
        try
        {
            String line;
            br = new BufferedReader(new FileReader("ability.txt"));
            try
            {
                while((line = br.readLine()) != null)
                {
                    if(!line.equals("****"))
                    {
                        ArrayList<String> value = new ArrayList<>();
                        StringTokenizer tokenizer = new StringTokenizer(line , ":");
                        String input = tokenizer.nextToken();
                        while(tokenizer.hasMoreElements())
                        {
                            value.add(tokenizer.nextToken());
                        }
                        mapDataAbility.put(input,value);
                    }
                    else
                    {
                        ArrayList<String> temp = new ArrayList<>();
                        temp = (ArrayList<String>)mapDataAbility.get("isCastable");
                        if(temp.get(0).equals("true"))
                        {
                            ability = new CastableAbility();
                            ability.setCastable(true);
                        }
                        else{
                            ability = new Ability();
                            ability.setCastable(false);
                        }
                        temp = (ArrayList<String>)mapDataAbility.get("abilityName");
                        ability.setName(temp.get(0));
                        temp=(ArrayList<String>)mapDataAbility.get("price");
                        for (String s : temp) {
                            Price price = new Price(Integer.parseInt(s));
                            ability.setAcquirePrices(price);
                        }
                        temp = (ArrayList<String>)mapDataAbility.get("abilityInfo");
                        ability.setAbilityInfo(temp.get(0));

                        temp = (ArrayList<String>)mapDataAbility.get("prerequisiteAbility");
                        for (int i = 0; i <temp.size() ; i+=2) {
                            PrerequisiteAbility prerequisiteAbility = new PrerequisiteAbility(temp.get(i),Integer.parseInt(temp.get(i+1)));
                            ability.setPrerequisiteAbilities(prerequisiteAbility);
                        }
                        if(ability.isCastable())
                        {
                            temp = (ArrayList<String>) mapDataAbility.get("castPrice");
                            for (int i = 0; i < temp.size(); i += 5) {
                                Price price = new Price(Integer.parseInt(temp.get(i)), Integer.parseInt(temp.get(i + 1)), Integer.parseInt(temp.get(i + 2)), Integer.parseInt(temp.get(i + 3)), Integer.parseInt(temp.get(i + 4)));
                                ability.addToCastPrice(price);
                            }
                        }
                        else
                        {
                            temp = (ArrayList<String>) mapDataAbility.get("buffs");
                            for (int i = 0; i < temp.size(); i+= 8) {
                                Buff buff = new Buff(temp.get(i),Integer.parseInt(temp.get(i+1)),Integer.parseInt(temp.get(i+2)),Integer.parseInt(temp.get(i+3)),Integer.parseInt(temp.get(i+4)),Integer.parseInt(temp.get(i+5)),Integer.parseInt(temp.get(i+6)),Integer.parseInt(temp.get(i+7)));
                                ability.addToBuff(buff);
                            }
                        }
                        abilities.add(ability);
                        mapDataAbility.clear();


                    }
                }
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return abilities;
    }
    public static ArrayList<Hero> loadHero()
    {
        Hero hero = new Hero();
        ArrayList<Hero> heroes = new ArrayList<>();
        BufferedReader br;
        Map<String,String> mapingData = new HashMap<>();
        try{
            br = new BufferedReader(new FileReader("heroes.txt"));
            String line;
            try {
                while ((line = br.readLine()) != null)
                {
                    if (!line.equals("****"))
                    {
                        StringTokenizer temp = new StringTokenizer(line, ":");
                        String key = temp.nextToken();
                        String value = temp.nextToken();
                        mapingData.put(key, value);
                    }
                    else
                    {
                        hero.setName(mapingData.get("heroName"));
                        hero.setEnergyPoints(Integer.parseInt( mapingData.get("energy points")));
                        hero.getType().setMaximumHealth(Integer.parseInt(mapingData.get("maximum health")));
                        hero.getType().setHealthRefillRatePercentage(Integer.parseInt(mapingData.get("health refill rate")));
                        hero.getType().setMaximumMagic(Integer.parseInt(mapingData.get("maximum magic")));
                        hero.getType().setMagicRefillRatePercentage(Integer.parseInt(mapingData.get("magic refill rate")));
                        hero.getType().setAttackPower(Integer.parseInt(mapingData.get("attack power")));
                        hero.getType().setInventorySize(Integer.parseInt(mapingData.get("inventory size")));
                        heroes.add(hero);
                        mapingData.clear();
                    }
                }
                br.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return heroes;
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

    public Story getStory() {
        return story;
    }

    public GameObjectsHolder getGameObjectsHolder()
    {
        return gameObjectsHolder;
    }
}
