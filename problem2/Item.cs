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