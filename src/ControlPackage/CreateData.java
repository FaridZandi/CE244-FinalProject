package ControlPackage;

import ModelPackage.Ability;
import ModelPackage.Battle;
import ModelPackage.Buff;
import ModelPackage.BuffCreatorItem;
import ModelPackage.CastableAbility;
import ModelPackage.CastableData;
import ModelPackage.CastableItem;
import ModelPackage.Hero;
import ModelPackage.Item;
import ModelPackage.Model;
import ModelPackage.PrerequisiteAbility;
import ModelPackage.Price;
import ModelPackage.SoldierType;
import ModelPackage.TraitChangerItem;
import java.util.ArrayList;

/**
 * Created by Y50 on 6/14/2016.
 */
public class CreateData
{

    public static void writeBattles()
    {
        Battle battle1 = new Battle("You’ve entered the castle, it takes a while for your eyes to get used to the darkness but the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and the growling of thugs tell you that your first battle has BEGUN!" , "3 weak thugs - 1 weak angel" , 20 , 50);
        Battle battle2 = new Battle("As you wander into the hall you realize the surrounding doors can lead your destiny to something far worse than you expected. You know what’s anticipating you behind the only open door but there’s no other choice." , "2 able thugs - 1 weak angel - 1 weak tank" , 25 , 60);
        Battle battle3 = new Battle("The door behind you is shut with a thunderous sound and you progress into the next hall holding the first key that you’ve found, hoping to seek the second one." , " 1 able thug – 1 mighty thug - 1 able angel - 1 weak tank" , 30 , 70);
        Battle battle4 = new Battle("Running with the second key in your hand, you unlock the door back to the first hall and use the first key to burst into your most terrifying nightmares." , "2 mighty thugs - 1 able angel - 2 able tanks" , 35 , 80);
        ArrayList<Battle> battles = new ArrayList<>();
        battles.add(battle1);
        battles.add(battle2);
        battles.add(battle3);
        battles.add(battle4);
        Model.writeInFile("battles.txt" , Model.encodeObject(battles));
    }
    public static void writeItems()
    {


        TraitChangerItem toughen = new TraitChangerItem("Toughen" , "all" , new Price(4) , 2 ,      new Buff("Item Toughen", 0, 20, 0, 0, 0, 0, 0));
        TraitChangerItem guide = new TraitChangerItem("Guide" , "all" , new Price(4) , 2 ,          new Buff("Item Guide", 0, 0, 20, 0, 0, 0, 0));
        TraitChangerItem defy = new TraitChangerItem("Defy" , "all" , new Price(4) , 2 ,            new Buff("Item Defy", 8, 0, 0, 0, 0, 0, 0));

        BuffCreatorItem sword = new BuffCreatorItem("Sword" , "all" , new Price(25) , 0 ,              new Buff("Item Sword",          80, 0, 0, 0, 0, 0, 0));
        BuffCreatorItem energyboots = new BuffCreatorItem("Energy Boots" , "all" , new Price(20) , 0 , new Buff("Item Energy Boots",   0, 0, 0, 1, 0, 0, 0));
        BuffCreatorItem armor = new BuffCreatorItem("Armor" , "all" , new Price(25) , 0 ,              new Buff("Item Armor",          0, 200, 0, 0, 0, 0, 0));
        BuffCreatorItem magicstick = new BuffCreatorItem("Magic Stick" , "all" , new Price(28) , 0 ,   new Buff("Item Magic stick",    0, 0, 150, 0, 0, 0, 0));


        CastableItem healthpotion = new CastableItem("Health Potion" , "all" , new Price(15) , 0 , null , 3);
        healthpotion.setCastableData(new CastableData(0, 100, 0, 0, 0, 0, false , false , false, null));
        CastableItem magicpotion = new CastableItem("Magic Potion" , "all" , new Price(15) , 0 , null , 3);
        magicpotion.setCastableData(new CastableData(0, 0, 50, 0, 0, 0, false , false , false, null));

        ArrayList<Item> items = new ArrayList<>();
        items.add(toughen);
        items.add(guide);
        items.add(defy);
        items.add(sword);
        items.add(energyboots);
        items.add(armor);
        items.add(magicstick);
        items.add(healthpotion);
        items.add(magicpotion);
        Model.writeInFile("items.txt",Model.encodeObject(items));

    }


    public static void writeSoldierTypes()
    {

        ArrayList<Price> acquirePrices1 = new ArrayList<>();
        acquirePrices1.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices1.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices1.add(new Price(0, 4 , 0 , 0 , 0));
        ArrayList<Buff> affectingBuffs1 = new ArrayList<>();
        affectingBuffs1.add(new Buff("Fight Training Buff Level 1" , 30 , 0 , 0 , 0 , 0 , 0, 0 ));
        affectingBuffs1.add(new Buff("Fight Training Buff Level 2" , 30 , 0 , 0 , 0 , 0 , 0, 0 ));
        affectingBuffs1.add(new Buff("Fight Training Buff Level 3" , 30 , 0 , 0 , 0 , 0 , 0, 0 ));
        Ability FightTraining = new Ability("Fight Training" , acquirePrices1 , null , false , affectingBuffs1 , "Permanently increases attack power.");
        ArrayList<Price> acquirePrices2 = new ArrayList<>();
        acquirePrices2.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices2.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices2.add(new Price(0, 4 , 0 , 0 , 0));
        ArrayList<Buff> affectingBuffs2 = new ArrayList<>();
        affectingBuffs2.add(new Buff("Work out Buff Level 1" , 0, 50 , 0 , 0 , 0 , 0, 0 ));
        affectingBuffs2.add(new Buff("Work out Buff Level 2" , 0, 50 , 0 , 0 , 0 , 0, 0 ));
        affectingBuffs2.add(new Buff("Work out Buff Level 3" , 0, 50 , 0 , 0 , 0 , 0, 0 ));
        Ability Workout = new Ability("Work out" , acquirePrices2 , null , false , affectingBuffs2 , "Permanently increases maximum health.");
        ArrayList<Ability> abilities1 = new ArrayList<>();
        abilities1.add(Workout);
        abilities1.add(FightTraining);
        SoldierType fighter = new SoldierType("Fighter" , abilities1, null, 120 , 200 , 120 , 2 , 6 , 10, 5);



        ArrayList<Price> acquirePrices3 = new ArrayList<>();
        acquirePrices3.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices3.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices3.add(new Price(0, 4 , 0 , 0 , 0));
        ArrayList<Buff> affectingBuffs3 = new ArrayList<>();
        affectingBuffs3.add(new Buff("Quick As A Bunny Buff Level 1" , 0 , 0 , 0 , 1 , 0 , 0, 0 ));
        affectingBuffs3.add(new Buff("Quick As A Bunny Buff Level 2" , 0 , 0 , 0 , 1 , 0 , 0, 0 ));
        affectingBuffs3.add(new Buff("Quick As A Bunny Buff Level 3" , 0 , 0 , 0 , 1 , 0 , 0, 0 ));
        Ability quickAsABunny = new Ability("Quick As A Bunny" , acquirePrices3 , null , false , affectingBuffs3 , "Permanently increases attack power.");
        ArrayList<Price> acquirePrices4 = new ArrayList<>();
        acquirePrices4.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices4.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices4.add(new Price(0, 4 , 0 , 0 , 0));
        ArrayList<Buff> affectingBuffs4 = new ArrayList<>();
        affectingBuffs4.add(new Buff("Magic Lessons Buff Level 1" , 0, 0 , 50 , 0 , 0 , 0, 0 ));
        affectingBuffs4.add(new Buff("Magic Lessons Buff Level 2" , 0, 0 , 50 , 0 , 0 , 0, 0 ));
        affectingBuffs4.add(new Buff("Magic Lessons Buff Level 3" , 0, 0 , 50 , 0 , 0 , 0, 0 ));
        Ability magicLessons = new Ability("Magic Lessons" , acquirePrices4 , null , false , affectingBuffs4 , "Permanently increases maximum health.");
        ArrayList<Ability> abilities2 = new ArrayList<>();
        abilities2.add(quickAsABunny);
        abilities2.add(magicLessons);
        SoldierType supporter = new SoldierType("Supporter" , abilities2, null, 120 , 200 , 120 , 2 , 6 , 10, 5);




        SoldierType weakThug = new SoldierType  ("Weak Thug" , null , null , 50 , 200 , 0, 0 , 2 , 0 , 0);
        SoldierType ableThug = new SoldierType  ("Able Thug" , null , null , 90 , 300 , 0, 0 , 2 , 0 , 0);
        SoldierType mightyThug = new SoldierType("Mighty Thug" , null , null , 150 , 400 , 0, 0 , 2 , 0 , 0);

        ArrayList<CastableData> castableData1 = new ArrayList<>();
        castableData1.add(new CastableData(0 , 100 , 0 , 0 , 0 , 0,false , false , true , null));
        ArrayList<CastableData> castableData2 = new ArrayList<>();
        castableData1.add(new CastableData(0 , 150 , 0 , 0 , 0 , 0,false , false , true , null));
        ArrayList<Price> castPrice1 = new ArrayList<>();
        castPrice1.add(new Price(0 , 0 , 2 , 0 , 0));
        ArrayList<Price> castPrice2 = new ArrayList<>();
        castPrice2.add(new Price(0 , 0 , 2 , 0 , 0));

        ArrayList<Ability> heal1 = new ArrayList<>();
        CastableAbility temp1= new CastableAbility("Heal Level 1" , null , null ,false , null , "Heals Other Soldiers" , castPrice1 , castableData1 , "“Angel just healed “ + (target) + “ with “ + (healing amount) + “ health points”" , true , false);
        temp1.setLevel(1);
        heal1.add(temp1);

        ArrayList<Ability> heal2 = new ArrayList<>();
        CastableAbility temp2 = new CastableAbility("Heal Level 2" , null , null ,false , null , "Heals Other Soldiers" , castPrice2 , castableData2 , "“Angel just healed “ + (target) + “ with “ + (healing amount) + “ health points”" , true , false);
        temp2.setLevel(1);
        heal2.add(temp2);

        SoldierType weakAngel = new SoldierType("Weak Angel" , heal1 , null , 0 , 150 , 0, 0 , 2 , 0 , 0);
        SoldierType AbleAngel = new SoldierType("Able Angel" , heal2 , null , 0 , 250 , 0, 0 , 2 , 0 , 0);

        ArrayList<Buff> buff1= new ArrayList<>();
        buff1.add(new Buff("Weak Tank Buff", true , 0 , 0, 0 , 0 , 0 , 0 ,0 , 100));
        ArrayList<Buff> buff2= new ArrayList<>();
        buff2.add(new Buff("Able Tank Buff", true , 0 , 0, 0 , 0 , 0 , 0 ,0 , 100));
        SoldierType weakTank = new SoldierType("Weak Tank", null , buff1 , 30 , 400 , 0 , 0 , 2  , 0 , 0);
        SoldierType AbleTank = new SoldierType("Able Tank", null , buff2 , 900 , 500 , 0 , 0 , 2  , 0 , 0);



        ArrayList<SoldierType> soldierTypes = new ArrayList<>();
        soldierTypes.add(fighter);
        soldierTypes.add(supporter);
        soldierTypes.add(weakThug);
        soldierTypes.add(ableThug);
        soldierTypes.add(mightyThug);
        soldierTypes.add(weakAngel);
        soldierTypes.add(AbleAngel);
        soldierTypes.add(weakTank);
        soldierTypes.add(AbleTank);


        Model.writeInFile("soldierTypes.txt" , Model.encodeObject(soldierTypes));
    }
    public static void writeHeroes()
    {
        ArrayList<Price> acquirePrices1 = new ArrayList<>();
        acquirePrices1.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices1.add(new Price(0, 4 , 0 , 0 , 0));
        acquirePrices1.add(new Price(0, 6 , 0 , 0 , 0));
        ArrayList<PrerequisiteAbility> prerequisiteAbilities1 = new ArrayList<>();
        prerequisiteAbilities1.add(new PrerequisiteAbility("Fight Training", 1));
        prerequisiteAbilities1.add(new PrerequisiteAbility("Fight Training", 2));
        prerequisiteAbilities1.add(new PrerequisiteAbility("Fight Training", 3));
        ArrayList<Price> castPrices1 = new ArrayList<>();
        castPrices1.add(new Price(0 , 0 , 2 , 50 , 0));
        castPrices1.add(new Price(0 , 0 , 2 , 50 , 0));
        castPrices1.add(new Price(0 , 0 , 2 , 50 , 0));
        ArrayList<CastableData> castableDatas1 = new ArrayList<>();
        castableDatas1.add(new CastableData(0 , 0 , 0 , 0 , 0 , 1.2 , false , false , false , null));
        castableDatas1.add(new CastableData(0 , 0 , 0 , 0 , 0 , 1.4 , false , false , false , null));
        castableDatas1.add(new CastableData(0 , 0 , 0 , 0 , 0 , 1.6 , false , false , false , null));

        CastableAbility FightTraining = new CastableAbility("OverPowered Attack" , acquirePrices1 , prerequisiteAbilities1 , false , null , "Attacks an enemy with N times power for 2 energy points and 50 magic points", castPrices1 , castableDatas1 , "“Eley just did an overpowered attack on ” + (target) + “ with ” + (damage done) + “ damage”" , true , false);

        ArrayList<Price> acquirePrices2 = new ArrayList<>();
        acquirePrices2.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices2.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices2.add(new Price(0, 4 , 0 , 0 , 0));
        ArrayList<Buff> affectingBuffs2 = new ArrayList<>();
        affectingBuffs2.add(new Buff("Swirling Attack Buff Level 1" ,true, 0, 0 , 0 , 0 , 0 , 0 , 0 , 10));
        affectingBuffs2.add(new Buff("Swirling Attack Buff Level 2" ,true, 0, 0 , 0 , 0 , 0 , 0 , 0 , 20));
        affectingBuffs2.add(new Buff("Swirling Attack Buff Level 3" ,true, 0, 0 , 0 , 0 , 0 , 0 , 0 , 30));
        ArrayList<PrerequisiteAbility> prerequisiteAbilities2 = new ArrayList<>();
        prerequisiteAbilities2.add(new PrerequisiteAbility("Work Out", 1));

        Ability swrilingAttack = new Ability("Swirling Attacks" , acquirePrices2 , prerequisiteAbilities2 , true , affectingBuffs2 , "While attacking, non-targeted enemies also take P percent of its damage");

        ArrayList<Ability> abilities1 = new ArrayList<>();
        abilities1.add(FightTraining);
        abilities1.add(swrilingAttack);
        Hero Eley = new Hero("Fighter", "Eley" , abilities1);

        ArrayList<Price> acquirePrices3 = new ArrayList<>();
        acquirePrices3.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices3.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices3.add(new Price(0, 4 , 0 , 0 , 0));
        ArrayList<PrerequisiteAbility> prerequisiteAbilities3 = new ArrayList<>();
        prerequisiteAbilities3.add(new PrerequisiteAbility("Work Out", 1));
        prerequisiteAbilities3.add(new PrerequisiteAbility("Work Out", 2));
        prerequisiteAbilities3.add(new PrerequisiteAbility("Work Out", 3));
        ArrayList<Price> castPrices2 = new ArrayList<>();
        castPrices2.add(new Price(0 , 0 , 3 , 60 , 40));
        castPrices2.add(new Price(0 , 0 , 3 , 60 , 50));
        castPrices2.add(new Price(0 , 0 , 3 , 60 , 60));
        ArrayList<CastableData> castableDatas2 = new ArrayList<>();
        castableDatas2.add(new CastableData(120 , 0 , 0 , 0 , 1 , 0 , false , true , false , null));
        castableDatas2.add(new CastableData(150 , 0 , 0 , 0 , 1 , 0 , false , true , false , null));
        castableDatas2.add(new CastableData(180 , 0 , 0 , 0 , 1 , 0 , false , true , false , null));

        CastableAbility Sacrifice = new CastableAbility("Sacrifice" , acquirePrices3 , prerequisiteAbilities3 , false , null , "Damages all the enemies with 3H power at the cost of H of his own health, needs 3 energy points, 60 magic points and has a 1 turn cooldown", castPrices2 , castableDatas2 , "Chrome just sacrificed himself to damage all his enemies with “ + (damage done) + “ power“" , true , false);

        ArrayList<Price> acquirePrices4 = new ArrayList<>();
        acquirePrices4.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices4.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices4.add(new Price(0, 4 , 0 , 0 , 0));
        ArrayList<Buff> affectingBuffs4 = new ArrayList<>();
        affectingBuffs4.add(new Buff("Critical Strike Buff Level 1" ,true, 0, 0 , 0 , 0 , 0 , 0 , 0 , 10));
        affectingBuffs4.add(new Buff("Critical Strike Buff Level 2" ,true, 0, 0 , 0 , 0 , 0 , 0 , 0 , 20));
        affectingBuffs4.add(new Buff("Critical Strike Buff Level 3" ,true, 0, 0 , 0 , 0 , 0 , 0 , 0 , 30));
        ArrayList<PrerequisiteAbility> prerequisiteAbilities4 = new ArrayList<>();
        prerequisiteAbilities4.add(new PrerequisiteAbility("Fight Training", 1));

        Ability criticalStrike = new Ability("Critical Strike" , acquirePrices4 , prerequisiteAbilities4 , true , affectingBuffs4 , "Has a permanent P percent chance of doing an attack with double power");

        ArrayList<Ability> abilities2 = new ArrayList<>();
        abilities2.add(Sacrifice);
        abilities2.add(criticalStrike);

        Hero Chrome = new Hero("Fighter", "Chrome" , abilities2);


        ArrayList<Price> acquirePrices5 = new ArrayList<>();
        acquirePrices5.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices5.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices5.add(new Price(0, 5 , 0 , 0 , 0));
        ArrayList<PrerequisiteAbility> prerequisiteAbilities5 = new ArrayList<>();
        prerequisiteAbilities5.add(new PrerequisiteAbility("Magic Lessons", 1));
        prerequisiteAbilities5.add(new PrerequisiteAbility("Magic Lessons", 2));
        prerequisiteAbilities5.add(new PrerequisiteAbility("Magic Lessons", 3));
        ArrayList<Price> castPrices5 = new ArrayList<>();
        castPrices5.add(new Price(0 , 0 , 2 , 60 , 0));
        castPrices5.add(new Price(0 , 0 , 2 , 60 , 0));
        castPrices5.add(new Price(0 , 0 , 2 , 60 , 0));
        ArrayList<CastableData> castableDatas5 = new ArrayList<>();
        castableDatas5.add(new CastableData(0 , 100 , 0 , 0 , 1 , 0 , false , false , false , null));
        castableDatas5.add(new CastableData(0 , 150 , 0 , 0 , 1 , 0 , false , false , false , null));
        castableDatas5.add(new CastableData(0 , 150 , 0 , 0 , 0 , 0 , false , false , false , null));

        CastableAbility Elixir = new CastableAbility("Elixir" , acquirePrices5 , prerequisiteAbilities5 , false , null , "Refills H points of her own health or an ally’s, for 2 energy points and 60 magic points", castPrices5 , castableDatas5 , "“Meryl just healed “ + (target) + “ with “ + (healing amount) + “ health points”" , false , true);


        ArrayList<Price> acquirePrices6 = new ArrayList<>();
        acquirePrices6.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices6.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices6.add(new Price(0, 5 , 0 , 0 , 0));
        ArrayList<PrerequisiteAbility> prerequisiteAbilities6 = new ArrayList<>();
        prerequisiteAbilities6.add(new PrerequisiteAbility("Quick As A Bunny", 1));
        prerequisiteAbilities6.add(new PrerequisiteAbility("Quick As A Bunny", 2));
        prerequisiteAbilities6.add(new PrerequisiteAbility("Quick As A Bunny", 3));
        ArrayList<Price> castPrices6 = new ArrayList<>();
        castPrices6.add(new Price(0 , 0 , 2 , 30 , 0));
        castPrices6.add(new Price(0 , 0 , 2 , 30 , 0));
        castPrices6.add(new Price(0 , 0 , 1 , 30 , 0));
        ArrayList<CastableData> castableDatas6 = new ArrayList<>();
        castableDatas6.add(new CastableData(0 , 0, 0 , 0 , 1 , 0 , false , false , false , new Buff("Caretaker Buff Level 1",false, 1, 0, 0, 0, 1, 0, 0, 0)));
        castableDatas6.add(new CastableData(0 , 0, 0 , 0 , 0 , 0 , false , false , false , new Buff("Caretaker Buff Level 2",false, 1, 0, 0, 0, 1, 0, 0, 0)));
        castableDatas6.add(new CastableData(0 , 0, 0 , 0 , 0 , 0 , false , false , false , new Buff("Caretaker Buff Level 3",false, 1, 0, 0, 0, 1, 0, 0, 0)));

        CastableAbility CareTaker = new CastableAbility("Caretaker" , acquirePrices6 , prerequisiteAbilities6 , false , null , "Gives 1 energy point to an ally for 30 magic points (this ep does not last until the end of the battle and is only usable during the current turn)", castPrices6 , castableDatas6 , "“Meryl just gave “ + (target) + “ 1 energy point”" , false , true);

        ArrayList<Ability> abilities3 = new ArrayList<>();
        abilities3.add(Elixir);
        abilities3.add(CareTaker);
        Hero Meryl = new Hero("Supporter" , "Meryl" , abilities3);

        ArrayList<Price> acquirePrices7 = new ArrayList<>();
        acquirePrices7.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices7.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices7.add(new Price(0, 5 , 0 , 0 , 0));
        ArrayList<Price> castPrices7 = new ArrayList<>();
        castPrices7.add(new Price(0 , 0 , 2 , 50 , 0));
        castPrices7.add(new Price(0 , 0 , 2 , 50 , 0));
        castPrices7.add(new Price(0 , 0 , 2 , 50 , 0));
        ArrayList<CastableData> castableDatas7 = new ArrayList<>();
        castableDatas7.add(new CastableData(0 , 0, 0 , 0 , 1 , 0 , false , false , false , new Buff("Boost Buff Level 1",false, Integer.MAX_VALUE, 20, 0, 0, 0, 0, 0, 0)));
        castableDatas7.add(new CastableData(0 , 0, 0 , 0 , 1 , 0 , false , false , false , new Buff("Boost Buff Level 2",false, Integer.MAX_VALUE, 30, 0, 0, 0, 0, 0, 0)));
        castableDatas7.add(new CastableData(0 , 0, 0 , 0 , 0 , 0 , false , false , false , new Buff("Boost Buff Level 3",false, Integer.MAX_VALUE, 50, 0, 0, 0, 0, 0, 0)));


        CastableAbility Boost = new CastableAbility("Boost" , acquirePrices7 , null , false , null , "Gives X bonus attack power to himself or an ally, which lasts till the end of the battle, for 2 energy points and 50 magic points (this bonus attack power can stack up)", castPrices7 , castableDatas7 , "“Bolti just boosted “ + (target) + “ with “ + (A) + “ power”" , false , true);

        ArrayList<Price> acquirePrices8 = new ArrayList<>();
        acquirePrices8.add(new Price(0, 2 , 0 , 0 , 0));
        acquirePrices8.add(new Price(0, 3 , 0 , 0 , 0));
        acquirePrices8.add(new Price(0, 4 , 0 , 0 , 0));
        ArrayList<PrerequisiteAbility> prerequisiteAbilities8 = new ArrayList<>();
        prerequisiteAbilities8.add(new PrerequisiteAbility("Magic Lessons", 1));
        prerequisiteAbilities8.add(new PrerequisiteAbility("Magic Lessons", 2));
        prerequisiteAbilities8.add(new PrerequisiteAbility("Magic Lessons", 3));
        ArrayList<Price> castPrices8 = new ArrayList<>();
        castPrices8.add(new Price(0 , 0 , 1 , 50 , 0));
        castPrices8.add(new Price(0 , 0 , 1 , 50 , 0));
        castPrices8.add(new Price(0 , 0 , 1 , 50 , 0));
        ArrayList<CastableData> castableDatas8 = new ArrayList<>();
        castableDatas8.add(new CastableData(0 , 0, 50 , 0 , 1 , 0 , false , false , false , null));
        castableDatas8.add(new CastableData(0 , 0, 80 , 0 , 1 , 0 , false , false , false , null));
        castableDatas8.add(new CastableData(0 , 0, 80 , 0 , 0 , 0 , false , false , false , null));

        CastableAbility ManaBeam = new CastableAbility("Mana Beam" , acquirePrices8 , prerequisiteAbilities8 , false , null , "Gives M magic points to himself or an ally for 1 energy point and 50 magic points", castPrices8 , castableDatas8 , "“Bolti just helped “ + (target) + “ with “ + (M) + “ magic points”" , false , true);

        ArrayList<Ability> abilities4 = new ArrayList<>();
        abilities4.add(Boost);
        abilities4.add(ManaBeam);
        Hero Bolti = new Hero("Supporter" , "Bolti" , abilities4);

        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(Eley);
        heroes.add(Chrome);
        heroes.add(Meryl);
        heroes.add(Bolti);

        Model.writeInFile("heroes.txt", Model.encodeObject(heroes));
    }
}
