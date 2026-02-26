class Customer
{
    public string Name { get; set; }
    public List<Order> Orders { get; set; } = new List<Order>();

    public double ReturnTotalPrice()
    {
        double totalPrice = 0;

        foreach (Order order in Orders)
        {
            totalPrice += order.GetTotalPrice();
        }

        return totalPrice;
    }
}