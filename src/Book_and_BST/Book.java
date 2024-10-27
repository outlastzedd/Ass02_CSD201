package Book_and_BST;

public class Book
{
    protected String bcode, title;
    protected int qty, lended;
    protected double price;

    public Book() {}
    public Book(String bcode, String title, int qty, int lended, double price)
    {
        this.bcode = bcode;
        this.title = title;
        this.qty = qty;
        this.lended = lended;
        this.price = price;
    }

    public String getBcode()
    {
        return bcode;
    }

    public void setBcode(String bcode)
    {
        this.bcode = bcode;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

    public int getLended()
    {
        return lended;
    }

    public void setLended(int lended)
    {
        if (lended <= getQty())
            this.lended = lended;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
    @Override
    public String toString()
    {
        return "Book[bcode=" + bcode + ", title=" + title + ", qty=" + qty + ", lended=" + lended + ", price=" + price + "]";
    }
}
