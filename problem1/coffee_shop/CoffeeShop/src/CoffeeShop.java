import java.util.ArrayList;

public class CoffeeShop {
    private Barista[] baristas;

    public Barista[] getBaristas() {
        return baristas;
    }

    public void setBaristas(Barista[] baristas) {
        this.baristas = baristas;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to the CoffeeShop!");

//        CUSTOMER JOHN
        Customer customer1 = new Customer("John", Customer.CustomerType.REGULAR);

        Drink drink1 = new Drink(Drink.DrinkType.ESPRESSO, Drink.DrinkSize.SMALL);
        drink1.addDrinkExtras(Drink.DrinkExtras.SHOT_OF_ESPRESSO);
        drink1.addDrinkExtras(Drink.DrinkExtras.WHIPPED_CREAM);

        ArrayList<Drink> drinksJohn = new ArrayList<>();
        drinksJohn.add(drink1);

        customer1.setDrinks(drinksJohn);

        System.out.println(customer1);

        System.out.println("-----------------------------------------1-----------------------------------------");
//        CUSTOMER DOE
        Customer customer2 = new Customer("Doe", Customer.CustomerType.GOLD);
        Drink drink2 = new Drink(Drink.DrinkType.CAPPUCCINO, Drink.DrinkSize.MEDIUM);

        Drink drink3 = new Drink(Drink.DrinkType.LATTE, Drink.DrinkSize.LARGE);

        ArrayList<Drink> drinksDoe = new  ArrayList<>();
        drinksDoe.add(drink2);
        drinksDoe.add(drink3);
        customer2.setDrinks(drinksDoe);

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(customer1));
        orders.add(new Order(customer2));

        System.out.println("-----------------------------------------2-----------------------------------------");

        for (Order order : orders) {
            order.calculateFinalPrice();
            double finalPrice = order.getFinalPrice();
//            System.out.println("Final Price: " + finalPrice);
        }

        Barista barista1 = new Barista(new ArrayList<>(), "Alice");
        Barista barista2 = new Barista(new ArrayList<>(), "Bob");

        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setBaristas(new Barista[]{barista1, barista2});

        // ----- CUSTOMER JOHN -----
        customer1 = new Customer("John", Customer.CustomerType.REGULAR);
        drink1 = new Drink(Drink.DrinkType.ESPRESSO, Drink.DrinkSize.SMALL);
        drink1.addDrinkExtras(Drink.DrinkExtras.SHOT_OF_ESPRESSO);
        drink1.addDrinkExtras(Drink.DrinkExtras.WHIPPED_CREAM);
        drinksJohn = new ArrayList<>();
        drinksJohn.add(drink1);
        customer1.setDrinks(drinksJohn);

        // ----- CUSTOMER DOE -----
        customer2 = new Customer("Doe", Customer.CustomerType.GOLD);
        drink2 = new Drink(Drink.DrinkType.CAPPUCCINO, Drink.DrinkSize.MEDIUM);
        drink3 = new Drink(Drink.DrinkType.LATTE, Drink.DrinkSize.LARGE);
        drinksDoe = new ArrayList<>();
        drinksDoe.add(drink2);
        drinksDoe.add(drink3);
        customer2.setDrinks(drinksDoe);

        // ----- CREATE ORDERS AND ASSIGN BARISTAS -----
        Order order1 = new Order(customer1);
        order1.setBarista(barista1);  // Alice prepară pentru John

        Order order2 = new Order(customer2);
        order2.setBarista(barista2);  // Bob prepară pentru Doe

        // ----- ASSIGN ORDERS TO BARISTAS -----
        barista1.addOrder(order1);
        barista2.addOrder(order2);

        // ----- CALCULATE PRICES -----
        orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        System.out.println("-----------------------------------------3-----------------------------------------");
        for (Order order : orders) {
            order.calculateFinalPrice();
        }

        // ----- TEST BARISTA OUTPUT -----
        System.out.println("-----------------------------------------4-----------------------------------------");
        System.out.println("=== Barista Report ===");
        for (Barista barista : coffeeShop.getBaristas()) {
            System.out.println("Barista: " + barista.getBaristaName());
            for (Order order : barista.getOrders()) {
                System.out.println("  Prepared order for: " + order.getCustomer().getName()
                        + " | Price: " + order.getFinalPrice()
                        + " | Time: " + order.getFormattedTime());
            }
        }
    }
}