import java.util.ArrayList;

public class Barista {
    private ArrayList<Order> orders;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public String getBaristaName() {
        return baristaName;
    }

    public Barista(ArrayList<Order> orders, String baristaName) {
        this.orders = orders;
        this.baristaName = baristaName;
    }

    public void setBaristaName(String baristaName) {
        this.baristaName = baristaName;
    }

    private String baristaName;
}
