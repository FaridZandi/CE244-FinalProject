/**
 * Created by Y50 on 5/1/2016.
 */
public class Price {
    private int goldPrice;
    private int XPPrice;
    private int EPPrice;
    private int magicPrice;
    private int healthPrice;

    public Price(int goldPrice, int XPPrice, int EPPrice, int magicPrice, int healthPrice)
    {
        this.goldPrice = goldPrice;
        this.EPPrice = EPPrice;
        this.XPPrice = XPPrice;
        this.healthPrice = healthPrice;
        this.magicPrice = magicPrice;
    }

    public int getEPPrice() {
        return EPPrice;
    }

    public int getGoldPrice() {
        return goldPrice;
    }

    public int getXPPrice() {
        return XPPrice;
    }

    public int getMagicPrice() {
        return magicPrice;
    }

    public int getHealthPrice() {
        return healthPrice;
    }
}
