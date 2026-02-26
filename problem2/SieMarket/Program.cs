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

class Item
{
    public string ProductName { get; set; }
    public double UnitPrice { get; set; }
    public int Quantity { get; set; }

    public double GetTotalPrice()
    {
        return this.Quantity * this.UnitPrice;
    }
}

class SieMarket
{
    public List<Customer> Customers { get; set; } = new List<Customer>();
    public Dictionary<string, (int totalQuantity, double totalRevenue)> itemMap { get; set; } = new Dictionary<string, (int, double)>();

    public string GetNameOfRichestCustomer()
    {
        List<(double number, string name)> CustomerPricePair = new List<(double, string)>();

        foreach (Customer customer in Customers)
        {
            CustomerPricePair.Add((customer.ReturnTotalPrice(), customer.Name));
        }

        var sorted = CustomerPricePair.OrderBy(p => p.number).ToList();

        return sorted.Last().name;
    }

    public string GetPopularProducts()
    {
        foreach (Customer customer in Customers)
            foreach (Order order in customer.Orders)
                order.UpdateItemMap(itemMap);

        var sorted = itemMap.OrderByDescending(p => p.Value.totalQuantity).ToList();
        int maxQuantity = sorted.First().Value.totalQuantity;

        var mostPopular = sorted.Where(p => p.Value.totalQuantity == maxQuantity).ToList();

        string result = "";
        foreach (var item in mostPopular)
        {
            result += $"{item.Key} - Quantity: {item.Value.totalQuantity}, Revenue: {item.Value.totalRevenue}\n";
        }

        return result;
    }

    public static void Main(string[] args)
    {
        SieMarket market = new SieMarket();

// ---------------------------------JOHN---------------------------------

        Customer john = new Customer { Name = "John" };

        Order order1 = new Order();
        order1.Items.Add(new Item { ProductName = "Laptop", UnitPrice = 400.0, Quantity = 6 });
        order1.Items.Add(new Item { ProductName = "Mouse", UnitPrice = 50.0, Quantity = 2 });

        Order order2 = new Order();
        order2.Items.Add(new Item { ProductName = "Monitor", UnitPrice = 300.0, Quantity = 2 });

        john.Orders.Add(order1);
        john.Orders.Add(order2);

// ---------------------------------JANE---------------------------------
        Customer jane = new Customer { Name = "Jane" };

        Order order3 = new Order();
        order3.Items.Add(new Item { ProductName = "Phone", UnitPrice = 400.0, Quantity = 6 });

        jane.Orders.Add(order3);

        market.Customers.Add(john);
        market.Customers.Add(jane);

        Console.WriteLine("=== Order Prices ===");
        Console.WriteLine($"John Order1: {order1.GetTotalPrice()}");
        Console.WriteLine($"John Order2: {order2.GetTotalPrice()}");
        Console.WriteLine($"Jane Order3: {order3.GetTotalPrice()}");

        Console.WriteLine("\n=== Customer Totals ===");
        Console.WriteLine($"John total: {john.ReturnTotalPrice()}");
        Console.WriteLine($"Jane total: {jane.ReturnTotalPrice()}");

        Console.WriteLine("\n=== Richest Customer ===");
        Console.WriteLine($"Richest: {market.GetNameOfRichestCustomer()}\n");

        Console.WriteLine($"Top popular items in the market are: \n{market.GetPopularProducts()}");
    }
}

