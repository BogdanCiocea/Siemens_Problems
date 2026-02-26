import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Order {
    private Customer customer;
    private Barista barista;
    private double finalPrice;
    private int id;
    private Long timestamp;

    public Barista getBarista() {
        return barista;
    }

    public void setBarista(Barista barista) {
        this.barista = barista;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getFormattedTime() throws InterruptedException {
        LocalDateTime dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(this.getTimestamp()),
                ZoneId.systemDefault()
        );

        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    private static final int POINTS_PER_FREE_DRINK = 10;

    public Order(Customer customer) {
        this.customer = customer;
        this.finalPrice = 0;
        this.id =  new Random().nextInt(1000);
        this.timestamp = -1L;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void calculateFinalPrice() throws InterruptedException {
        this.finalPrice = 0;

        for (Drink drink : customer.getDrinks()) {
            if (this.customer.getPoints() > POINTS_PER_FREE_DRINK) {
                System.out.println("The customer " + customer.getName() + " will get points deducted and receive a free drink");
                this.customer.setPoints(this.customer.getPoints() - POINTS_PER_FREE_DRINK);
                continue;
            }

            this.finalPrice += drink.getPrice();
        }

        System.out.println("Final Price for " + customer.getName() + "    " + customer.getDrinks().toString() + " is: " + this.finalPrice);

        this.customer.addPoints(this.finalPrice);

        if (this.finalPrice > 5) {
            Thread.sleep(2000);
        }

        this.timestamp = System.currentTimeMillis();
    }
}
