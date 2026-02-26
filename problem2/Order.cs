class Order
{
    private const double DISCOUNT_THRESHOLD = 500.0;
    private const double DISCOUNT_PERCENTAGE = 0.10;

    public int Id { get; set; }
    public List<Item> Items { get; set; } = new List<Item>();

    public Customer Customer { get; set; }
    
    public DateTime Timestamp { get; set; } = DateTime.Now;

    public double GetTotalPrice()
    {
        double totalPrice = 0;

        foreach (Item item in Items)
        {
            totalPrice += item.GetTotalPrice();
        }

        if (totalPrice > DISCOUNT_THRESHOLD)
        {
            totalPrice *= (1 - DISCOUNT_PERCENTAGE);
        }

        return totalPrice;
    }

    public void UpdateItemMap(Dictionary<string, (int totalQuantity, double totalRevenue)> itemMap)
    {
        foreach (Item item in Items)
        {
            if (itemMap.ContainsKey(item.ProductName))
            {
                itemMap[item.ProductName] = (
                    itemMap[item.ProductName].totalQuantity + item.Quantity,
                    itemMap[item.ProductName].totalRevenue + item.GetTotalPrice()
                );
            } else
            {
                itemMap[item.ProductName] = (item.Quantity, item.GetTotalPrice());
            }
        }
    }
}