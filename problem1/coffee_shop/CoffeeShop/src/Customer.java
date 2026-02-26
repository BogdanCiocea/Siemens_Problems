import java.util.ArrayList;
import java.util.Arrays;

public class Customer {
    public enum CustomerType {
        REGULAR,
        GOLD
    }

    private String name;

    public String getName() {
        return name;
    }

    public Customer(String name, CustomerType customerType) {
        this.name = name;
        this.customerType = customerType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    private ArrayList<Drink> drinks;

    private int points;

    private CustomerType customerType;

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drink> drinks) {
        this.drinks = drinks;
        System.out.println("The drinks for " + this.getName() + " are " + drinks);

        for (Drink drink : drinks) {
            this.points += drink.getPrice();
        }

        if (customerType == CustomerType.GOLD) {
            this.points *= 2;
        }

        System.out.println("The points for " + this.getName() + " are " + this.points);
    }

    public int getPoints() {
        return this.points;
    }

    public void addPoints(double amountSpent) {
        int earned = (int) Math.floor(amountSpent);
        if (this.customerType == CustomerType.GOLD) earned *= 2;
        this.points += earned;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", drinks=" + drinks +
                ", points=" + points +
                ", customerType=" + customerType +
                '}';
    }
}
