package ModelPackage;
/**
 * Created by Y50 on 5/9/2016.
 */
public class PrerequisiteAbility {
    public PrerequisiteAbility(String abilityName , int abilityLevel)
    {
        this.abilityLevel = abilityLevel;
        this.abilityName = abilityName;
    }
    private String abilityName;
    private int abilityLevel;

    public String getAbilityName(){ return this.abilityName;}
    public int getAbilityLevel(){ return this.abilityLevel;}

}
