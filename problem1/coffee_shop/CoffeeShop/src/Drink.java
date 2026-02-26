import java.util.ArrayList;
import java.util.Arrays;

public class Drink {
    public boolean isHasAddedExtras() {
        return hasAddedExtras;
    }

    public void setHasAddedExtras(boolean hasAddedExtras) {
        this.hasAddedExtras = hasAddedExtras;
    }

    public enum DrinkType {
        ESPRESSO,
        LATTE,
        CAPPUCCINO,
    }

    public Drink(DrinkType drinkType, DrinkSize drinkSize) {
        this.price = 0;
        this.drinkType = drinkType;
        this.drinkSize = drinkSize;
//        this.hasAddedExtras = hasAddedExtras;
        this.drinkExtras = new ArrayList<>();
    }

    public enum DrinkSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    public enum DrinkExtras {
        SHOT_OF_ESPRESSO,
        VANILLA_SYRUP,
        CARAMEL_SYRUP,
        WHIPPED_CREAM,
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public DrinkSize getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(DrinkSize drinkSize) {
        this.drinkSize = drinkSize;
    }

    public ArrayList<DrinkExtras> getDrinkExtras() {
        return drinkExtras;
    }

    public void setDrinkExtras(ArrayList<DrinkExtras> drinkExtras) {
        this.drinkExtras = drinkExtras;
    }

    public void addDrinkExtras(DrinkExtras drinkExtras) {
        this.drinkExtras.add(drinkExtras);
    }

    private double price;
    private DrinkType drinkType;
    private DrinkSize drinkSize;
    private boolean hasAddedExtras = false;
    private ArrayList<DrinkExtras> drinkExtras;

    public double getPrice() {
        if (this.drinkType == DrinkType.ESPRESSO) price = 2.0;
        else if (this.drinkType == DrinkType.LATTE) price = 3.5;
        else if (this.drinkType == DrinkType.CAPPUCCINO) price = 3.0;

        if (this.drinkSize == DrinkSize.SMALL) price *= 1.0;
        else if (this.drinkSize == DrinkSize.MEDIUM) price *= 1.3;
        else if (this.drinkSize == DrinkSize.LARGE) price *= 1.6;

        if (!drinkExtras.isEmpty()) {
            for (DrinkExtras drinkExtra : this.drinkExtras) {
                if (drinkExtra == DrinkExtras.SHOT_OF_ESPRESSO) price += 0.3;
                if (drinkExtra == DrinkExtras.VANILLA_SYRUP) price += 0.5;
                if (drinkExtra == DrinkExtras.CARAMEL_SYRUP) price += 0.3;
                if (drinkExtra == DrinkExtras.WHIPPED_CREAM) price += 0.5;
            }
        }

        if (drinkExtras.isEmpty()) {
            System.out.println("Price for a " + this.drinkSize + " " + this.drinkType + " is " + price);
        } else {
            System.out.println("Price for a " + this.drinkSize + " " + this.drinkType + " with " + drinkExtras + " is " + price);
        }
        return price;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "price=" + price +
                ", drinkType=" + drinkType +
                ", drinkSize=" + drinkSize +
                ", hasAddedExtras=" + hasAddedExtras +
                ", drinkExtras=" + drinkExtras +
                '}';
    }
}
