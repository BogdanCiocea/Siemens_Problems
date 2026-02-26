import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;

public class CoffeeShopTest {

    @Test
    public void testEspressoSmallPrice() {
        Drink drink = new Drink(Drink.DrinkType.ESPRESSO, Drink.DrinkSize.SMALL);
        Assertions.assertEquals(2.0, drink.getPrice(), 0.01);
    }

    @Test
    public void testEspressoSmallWithExtras() {
        ArrayList<Drink.DrinkExtras> extras = new ArrayList<>();
        extras.add(Drink.DrinkExtras.SHOT_OF_ESPRESSO);
        extras.add(Drink.DrinkExtras.WHIPPED_CREAM);
        Drink drink = new Drink(Drink.DrinkType.ESPRESSO, Drink.DrinkSize.SMALL);
        Assertions.assertEquals(2, drink.getPrice(), 0.01);
    }

    @Test
    public void testCappuccinoMediumPrice() {
        Drink drink = new Drink(Drink.DrinkType.CAPPUCCINO, Drink.DrinkSize.MEDIUM);
        Assertions.assertEquals(3.9, drink.getPrice(), 0.01);
    }

    @Test
    public void testRegularCustomerEarnsPoints() throws InterruptedException {
        Customer customer = new Customer("John", Customer.CustomerType.REGULAR);
        ArrayList<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink(Drink.DrinkType.ESPRESSO, Drink.DrinkSize.SMALL));
        customer.setDrinks(drinks);

        Order order = new Order(customer);
        order.calculateFinalPrice();

        Assertions.assertEquals(4, customer.getPoints());
    }

    @Test
    public void testGoldCustomerEarnsDoublePoints() throws InterruptedException {
        Customer customer = new Customer("Doe", Customer.CustomerType.GOLD);
        ArrayList<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink(Drink.DrinkType.ESPRESSO, Drink.DrinkSize.SMALL));
        customer.setDrinks(drinks);

        Order order = new Order(customer);
        order.calculateFinalPrice();

        Assertions.assertEquals(8, customer.getPoints());
    }

    @Test
    public void testFreedrinkWithPoints() throws InterruptedException {
        Customer customer = new Customer("John", Customer.CustomerType.REGULAR);

        ArrayList<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink(Drink.DrinkType.ESPRESSO, Drink.DrinkSize.SMALL));
        customer.setDrinks(drinks);

        Order order = new Order(customer);
        order.calculateFinalPrice();

        Assertions.assertEquals(2.0, order.getFinalPrice(), 0.01);
        Assertions.assertEquals(4, customer.getPoints());
    }
}